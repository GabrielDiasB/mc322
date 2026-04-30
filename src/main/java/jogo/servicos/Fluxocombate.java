package jogo.servicos;

import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.eventos.Acampamento;
import jogo.eventos.Evento;
import jogo.interfacejogo.Interface;
import jogo.mapa.MapaCampanha;
import jogo.mapa.NoMapa;
import java.util.List;

public class Fluxocombate {
    private final Interface interfaceJogo;
    private final Craft servicoCraft;

    public Fluxocombate(Interface interfaceJogo, Craft servicoCraft) {
        this.interfaceJogo = interfaceJogo;
        this.servicoCraft = servicoCraft;
    }

    public void executarCampanha(Heroi heroi, DequeHeroi cartas) {
        MapaCampanha mapa = MapaCampanha.criarPadrao(servicoCraft);
        NoMapa posicaoAtual = mapa.getRaiz();
        posicaoAtual.marcarVisitado();
        int eventosConcluidos = 0;
        Evento acampamentoInicial = new Acampamento(servicoCraft);
        acampamentoInicial.iniciar(heroi, cartas, interfaceJogo);

        while (heroi.estaVivo()) {
            List<NoMapa> opcoes = posicaoAtual.getProximosNaoVisitados();
            if (opcoes.isEmpty()) break;

            NoMapa destino = escolherProximoNo(posicaoAtual, opcoes);
            destino.marcarVisitado();
            posicaoAtual = destino;

            heroi.expProgresso(eventosConcluidos / 2);
            heroi.resetarExp();

            Evento evento = posicaoAtual.criarEvento();
            if (evento != null) {
                boolean sobreviveu = evento.iniciar(heroi, cartas, interfaceJogo);
                if (!sobreviveu) break;
            }

            eventosConcluidos++;
            if (posicaoAtual.ehFinal()) break;
        }
        exibirFinal(heroi, posicaoAtual);
    }

    private NoMapa escolherProximoNo(NoMapa atual, List<NoMapa> opcoes) {
        interfaceJogo.limpar();
        System.out.println("\u001B[36m============================================================");
        System.out.println("|                     MAPA DA JORNADA                      |");
        System.out.println("============================================================\u001B[m");
        
        System.out.println("\u001B[33m>> Localização Atual:\u001B[m " + atual.getNome());
        System.out.println("\n\u001B[33m>> Escolha o próximo destino:\u001B[m");

        for (int i = 0; i < opcoes.size(); i++) {
            NoMapa no = opcoes.get(i);
            String label = no.ehFinal() ? " \u001B[31m[BOSS FINAL]\u001B[m" : "";
            System.out.println("[ " + (i + 1) + " ] " + no.getNome() + label);
        }
        
        System.out.println(); // Pula uma linha
        int escolha = interfaceJogo.leitura(1, opcoes.size()); // A interface já vai imprimir "Digite a opcao:"
        return opcoes.get(escolha - 1);
    }

    private void exibirFinal(Heroi heroi, NoMapa posicao) {
        interfaceJogo.limpar();
        if (heroi.estaVivo() && posicao.ehFinal()) {
            System.out.println("\u001B[1;32m>> VITÓRIA! Você derrotou o Boss Final e concluiu sua jornada!\u001B[m\n");
        } else if (!heroi.estaVivo()) {
            System.out.println("\u001B[1;31m>> GAME OVER! Você sucumbiu aos perigos da noite...\u001B[m\n");
        }
        interfaceJogo.aguardarEnter("\u001B[33m>> Pressione Enter para sair...\u001B[m");
    }
}