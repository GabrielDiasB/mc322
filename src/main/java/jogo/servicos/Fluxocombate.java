package jogo.servicos;
import jogo.batalha.*;
import jogo.cartas.*;
import jogo.entidades.*;
import jogo.interfacejogo.Interface;


/**
 * Orquestra o fluxo principal da campanha e dos combates noturnos.
 */
public class Fluxocombate {
    private final Interface interfaceJogo;
    private final Craft servicoCraft;

    /**
     * Cria o servico principal de batalha.
     *
     * @param interfaceJogo interface textual do jogo
     * @param servicoCraft fluxo de preparacao e crafting
     */
    public Fluxocombate(Interface interfaceJogo, Craft servicoCraft) {
        this.interfaceJogo = interfaceJogo;
        this.servicoCraft = servicoCraft;
    }

    /**
     * Executa a campanha completa com preparacao e quatro noites de combate.
     *
     * @param heroi heroi do jogador
     * @param cartas baralho do heroi
     */
    public void executarCampanha(Heroi heroi, DequeHeroi cartas) {
        for (int numBatalha = 0; numBatalha < 4; numBatalha++) {
            if (!heroi.estaVivo()) {
                break;
            }

            iniciarDia(numBatalha, heroi);
            servicoCraft.executarPreparacao(heroi, cartas);
            executarNoite(numBatalha, heroi, cartas);
        }

        if (heroi.estaVivo()) {
            System.out.println("\n\u001B[1;32mPARABÉNS!!! Você venceu as 4 batalhas e derrotou o boss final!\u001B[m\n");
        } else {
            System.out.println("\n\u001B[1;31mQUE PENA, você foi derrotado!\u001B[m");
            System.out.println("\u001B[1;31mNão foi dessa vez... Tente novamente!\u001B[m\n");
        }
    }

    private void iniciarDia(int numBatalha, Heroi heroi) {
        heroi.expProgresso(numBatalha);
        heroi.resetarExp();
        interfaceJogo.atualizaTela(heroi);

        if (numBatalha > 0) {
            interfaceJogo.destaque(">> Você venceu a " + numBatalha + "ª batalha e ganhou +" + numBatalha + "XP! Já amanheceu, continue progredindo!\n");
        } else {
            interfaceJogo.destaque(">> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\n");
        }
    }

    private void executarNoite(int numBatalha, Heroi heroi, DequeHeroi cartas) {
        DequeInimigo filaBatalhas = new DequeInimigo();
        cartas.inicializacao();
        cartas.comprarAtual();

        if (numBatalha == 3) {
            filaBatalhas.adicionaNoFim(CriaInimigos.criarBossFinal());
        } else {
            filaBatalhas.adicionaNoFim(CriaInimigos.gerarInimigoDaFase(numBatalha));
        }

        while (heroi.estaVivo() && !filaBatalhas.vazio()) {
            Inimigo inimigoEscolhido = filaBatalhas.removerDoInicio();
            executarBatalhaContraInimigo(numBatalha, heroi, cartas, inimigoEscolhido);
            heroi.limparEfeitos();
        }
    }

    private void executarBatalhaContraInimigo(int numBatalha, Heroi heroi, DequeHeroi cartas, Inimigo inimigoEscolhido) {
        Combate combate = new Combate(heroi, inimigoEscolhido);

        heroi.resetarExp();
        heroi.resetarEscudo();
        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(inimigoEscolhido);

        if (numBatalha == 3) {
            interfaceJogo.destaque(">> Noite 4/4. Batalha final contra " + inimigoEscolhido.getNome() + "!");
        } else {
            interfaceJogo.destaque(">> Noite " + (numBatalha + 1) + "/4:");
        }

        while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
            if (heroi.getExp() == heroi.getExpInicial()) {
                interfaceJogo.destaque(inimigoEscolhido.anunciarProximaAcao());
            }

            if (heroi.getExp() > 0) {
                executarTurnoJogador(heroi, cartas, inimigoEscolhido, combate);
            } else {
                executarTurnoInimigo(heroi, cartas, inimigoEscolhido, combate);
            }
        }
    }

    private void executarTurnoJogador(Heroi heroi, DequeHeroi cartas, Inimigo inimigoEscolhido, Combate combate) {
        System.out.println("\n\n=========== Escolha suas cartas ===========\n");
        cartas.mostraAtual();
        System.out.println("\n[ 0 ] Encerrar turno\n");
        System.out.println("===========================================\n");

        int opcao = interfaceJogo.leitura(0, 5);
        if (opcao == 0) {
            heroi.zerarExp();
            return;
        }

        while (cartas.getAtual().get(opcao - 1) == null) {
            System.out.println("Você já escolheu essa carta! Tente outra opção.\n");
            opcao = interfaceJogo.leitura(0, 5);
        }

        Carta carta = cartas.getAtual().get(opcao - 1);
        if (carta.getCusto() > heroi.getExp()) {
            interfaceJogo.atualizaTela(heroi);
            mostrarPainelVs(inimigoEscolhido);
            System.out.print("\u001B[31m>> Sem XP suficiente para essa carta!\u001B[m");
            return;
        }

        int dado = carta.usar(heroi, inimigoEscolhido, combate);
        String texto = carta.usarTexto(heroi, inimigoEscolhido, dado, carta.getNome());
        heroi.gastarExp(carta.getCusto());
        cartas.usar(opcao - 1);

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(inimigoEscolhido);
        interfaceJogo.destaque(">> " + texto);
    }

    private void executarTurnoInimigo(Heroi heroi, DequeHeroi cartas, Inimigo inimigoEscolhido, Combate combate) {
        String notificacao = "";
        notificacao += combate.notificar(EventoCombate.FIM_TURNO_JOGADOR);

        if (!inimigoEscolhido.estaVivo()) {
            return;
        }

        ResultadoAcaoInimigo resultadoAcao = inimigoEscolhido.executarTurno(heroi, combate);
        notificacao += combate.notificar(EventoCombate.FIM_TURNO_INIMIGO);

        heroi.resetarExp();
        heroi.resetarEscudo();
        cartas.descartarAtual();
        cartas.comprarAtual();

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(inimigoEscolhido);
        interfaceJogo.destaque(">> " + heroi.getNome() + " encerrou o turno! " + resultadoAcao.getMensagemCombate() + notificacao);
    }

    private void mostrarPainelVs(Inimigo inimigoEscolhido) {
        System.out.println("   \u001B[31mVS\u001B[m\n");
        System.out.println(interfaceJogo.obterArteInimigo(inimigoEscolhido.getNome()));
        inimigoEscolhido.atualiza();
    }
}
