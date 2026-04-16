package jogo.servicos;
import jogo.batalha.*;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.entidades.Inimigo;
import jogo.interfacejogo.Interface;
import jogo.mapa.MapaCampanha;
import jogo.mapa.NoMapa;


import java.util.List;


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
     * Executa a campanha completa usando um mapa de progressão.
     *
     * @param heroi heroi do jogador
     * @param cartas baralho do heroi
     */
    public void executarCampanha(Heroi heroi, DequeHeroi cartas) {
        MapaCampanha mapa = MapaCampanha.criarPadrao();
        NoMapa posicaoAtual = mapa.getRaiz();
        posicaoAtual.marcarVisitado();
        int batalhasVencidas = 0;

        while (heroi.estaVivo()) {
            List<NoMapa> opcoes = posicaoAtual.getProximosNaoVisitados();
            if (opcoes.isEmpty()) {
                break;
            }

            NoMapa destino = escolherProximoNo(posicaoAtual, opcoes);
            destino.marcarVisitado();
            posicaoAtual = destino;

            iniciarDia(batalhasVencidas, heroi, posicaoAtual);
            servicoCraft.executarPreparacao(heroi, cartas);

            Inimigo inimigo = posicaoAtual.criarInimigo();
            if (inimigo == null) {
                continue;
            }

            Batalha batalha = new Batalha(heroi, inimigo, cartas, interfaceJogo);
            boolean venceu = batalha.executar();
            heroi.limparEfeitos();

            if (!venceu) {
                break;
            }

                interfaceJogo.destaque("\n>> Você venceu o inimigo! O caminho adiante está livre.\n");
                interfaceJogo.aguardarEnter(">> Pressione Enter para avançar pela trilha... ");
            batalhasVencidas++;

            if (posicaoAtual.ehFinal()) {
                break;
            }
        }

        if (heroi.estaVivo() && posicaoAtual.ehFinal()) {
            System.out.println("\n\u001B[1;32mPARABÉNS!!! Você alcançou o destino final e derrotou o boss!\u001B[m\n");
        } else if (heroi.estaVivo()) {
            System.out.println("\n\u001B[1;33mCampanha encerrada: não há mais caminhos disponíveis no mapa.\u001B[m\n");
        } else {
            System.out.println("\n\u001B[1;31mQUE PENA, você foi derrotado!\u001B[m");
            System.out.println("\u001B[1;31mNão foi dessa vez... Tente novamente!\u001B[m\n");
        }
    }

    private void iniciarDia(int batalhasVencidas, Heroi heroi, NoMapa destino) {
        heroi.expProgresso(batalhasVencidas);
        heroi.resetarExp();
        interfaceJogo.atualizaTela(heroi);

        if (batalhasVencidas > 0) {
            interfaceJogo.destaque(">> Você venceu " + batalhasVencidas + " batalha(s). Já amanheceu, continue progredindo!\n");
        } else {
            interfaceJogo.destaque(">> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\n");
        }
        interfaceJogo.destaque(">> Próximo destino: " + destino.getNome() + " (profundidade " + destino.getProfundidade() + ")\n");
    }

    private NoMapa escolherProximoNo(NoMapa atual, List<NoMapa> opcoes) {
        interfaceJogo.limpar();
        interfaceJogo.titulo();
        System.out.println("\u001B[36m========== MAPA DA CAMPANHA ==========\u001B[m");
        System.out.println("Posição atual: " + atual.getNome());
        System.out.println("Escolha o próximo caminho:\n");

        for (int i = 0; i < opcoes.size(); i++) {
            NoMapa no = opcoes.get(i);
            String sufixoFinal = no.ehFinal() ? " [DESTINO FINAL]" : "";
            System.out.println("[ " + (i + 1) + " ] " + no.getNome() + " (profundidade " + no.getProfundidade() + ")" + sufixoFinal);
        }
        System.out.println("\n======================================");

        int escolha = interfaceJogo.leitura(1, opcoes.size());
        return opcoes.get(escolha - 1);
    }
}
