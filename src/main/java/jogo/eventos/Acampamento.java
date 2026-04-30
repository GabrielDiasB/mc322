package jogo.eventos;

import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;
import jogo.servicos.Craft;
import java.util.ArrayList;
import java.util.List;

public class Acampamento extends Evento {
    private List<ComandoAcampamento> opcoes;
    private String mensagemFeedback = "";

    public Acampamento(Craft servicoCraft) {
        opcoes = new ArrayList<>();
        
        opcoes.add(new ComandoAcampamento() {
            public void executar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
                if (heroi.getExp() >= 1) {
                    heroi.gastarExp(1);
                    mensagemFeedback = "\u001B[32m[!] Você explorou e encontrou: " + heroi.recursos() + "!\u001B[m";
                } else {
                    mensagemFeedback = "\u001B[31m[!] Energia (XP) insuficiente para explorar.\u001B[m";
                }
            }
            public String getDescricao() { return "Explorar Arredores      [-1 XP] -> Obter Recurso"; }
        });

        opcoes.add(new ComandoAcampamento() {
            public void executar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
                if (heroi.getExp() >= 1) {
                    heroi.gastarExp(1);
                    heroi.recuperar(15);
                    mensagemFeedback = "\u001B[32m[!] Você descansou e recuperou 15 de Vida!\u001B[m";
                } else {
                    mensagemFeedback = "\u001B[31m[!] Energia (XP) insuficiente para descansar.\u001B[m";
                }
            }
            public String getDescricao() { return "Descansar na Fogueira   [-1 XP] -> Recuperar +15 Vida"; }
        });

        opcoes.add(new ComandoAcampamento() {
            public void executar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
                mensagemFeedback = "";
                servicoCraft.abrirMesa(heroi, cartas);
            }
            public String getDescricao() { return "Acessar Mesa de Crafting        -> Criar Itens e Cartas"; }
        });
        
        opcoes.add(new ComandoAcampamento() {
            public void executar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
                mensagemFeedback = "\u001B[33m[!] Seguindo viagem...\u001B[m";
            }
            public String getDescricao() { return "Desmontar Acampamento           -> Seguir Viagem"; }
        });
    }

    @Override
    public boolean iniciar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
        heroi.resetarExp();
        mensagemFeedback = "";
        
        while (true) {
            interfaceJogo.limpar();
            System.out.println("\u001B[36m============================================================");
            System.out.println("|                    ACAMPAMENTO SEGURO                    |");
            System.out.println("============================================================\u001B[m");
            heroi.atualiza();
            System.out.println("\n" + heroi.inventario());
            
            if (!mensagemFeedback.isEmpty()) {
                System.out.println("\n" + mensagemFeedback);
            }

            System.out.println("\n\u001B[1;33m>> AÇÕES DISPONÍVEIS:\u001B[m");
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.println("[ " + (i + 1) + " ] " + opcoes.get(i).getDescricao());
            }

            System.out.println(); // Apenas pular uma linha para ficar organizado
            int escolha = interfaceJogo.leitura(1, opcoes.size()); // A Interface já imprime "Digite a opcao:"
            opcoes.get(escolha - 1).executar(heroi, cartas, interfaceJogo);

            if (escolha == 4) break; 
        }
        return heroi.estaVivo();
    }
}