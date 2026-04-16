package jogo.batalha;

import jogo.cartas.Carta;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.entidades.Inimigo;
import jogo.interfacejogo.Interface;


/**
 * Encapsula um combate individual entre herói e um único inimigo.
 */
public class Batalha {
    private Heroi heroi;
    private Inimigo inimigo;
    private DequeHeroi cartas;
    private Interface interfaceJogo;

    /**
     * Cria uma batalha.
     *
     * @param heroi herói do jogador
     * @param inimigo inimigo da batalha
     * @param cartas baralho do herói
     * @param interfaceJogo interface textual do jogo
     */
    public Batalha(Heroi heroi, Inimigo inimigo, DequeHeroi cartas, Interface interfaceJogo) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.cartas = cartas;
        this.interfaceJogo = interfaceJogo;
    }

    /**
     * Executa o combate até vitória do herói ou derrota.
     *
     * @return true quando o herói vence a batalha
     */
    public boolean executar() {
        Combate combate = new Combate(heroi, inimigo);
        cartas.inicializacao();
        cartas.comprarAtual();

        heroi.resetarExp();
        heroi.resetarEscudo();
        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs();

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            if (heroi.getExp() == heroi.getExpInicial()) {
                interfaceJogo.destaque(inimigo.anunciarProximaAcao());
            }

            if (heroi.getExp() > 0) {
                executarTurnoJogador(combate);
            } else {
                executarTurnoInimigo(combate);
            }
        }

        return heroi.estaVivo() && !inimigo.estaVivo();
    }

    private void executarTurnoJogador(Combate combate) {
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
            if (opcao == 0) {
                heroi.zerarExp();
                return;
            }
        }

        Carta carta = cartas.getAtual().get(opcao - 1);
        if (carta.getCusto() > heroi.getExp()) {
            interfaceJogo.atualizaTela(heroi);
            mostrarPainelVs();
            System.out.print("\u001B[31m>> Sem XP suficiente para essa carta!\u001B[m");
            return;
        }

        int dado = carta.usar(heroi, inimigo, combate);
        String texto = carta.usarTexto(heroi, inimigo, dado, carta.getNome());
        heroi.gastarExp(carta.getCusto());
        cartas.usar(opcao - 1);

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs();
        interfaceJogo.destaque(">> " + texto);
    }

    private void executarTurnoInimigo(Combate combate) {
        String notificacao = "";
        notificacao += combate.notificar(EventoCombate.FIM_TURNO_JOGADOR);

        if (!inimigo.estaVivo()) {
            return;
        }

        ResultadoAcaoInimigo resultadoAcao = inimigo.executarTurno(heroi, combate);
        notificacao += combate.notificar(EventoCombate.FIM_TURNO_INIMIGO);

        heroi.resetarExp();
        heroi.resetarEscudo();
        cartas.descartarAtual();
        cartas.comprarAtual();

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs();
        interfaceJogo.destaque(">> " + heroi.getNome() + " encerrou o turno! " + resultadoAcao.getMensagemCombate() + notificacao);
    }

    private void mostrarPainelVs() {
        System.out.println("   \u001B[31mVS\u001B[m\n");
        System.out.println(interfaceJogo.obterArteInimigo(inimigo.getNome()));
        inimigo.atualiza();
    }
}