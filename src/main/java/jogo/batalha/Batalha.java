package jogo.batalha;

import jogo.cartas.Carta;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.entidades.Inimigo;
import jogo.interfacejogo.Interface;
import jogo.eventos.Evento;

import java.util.Random;

public class Batalha extends Evento {
    private Inimigo inimigo;

    public Batalha(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    @Override
    public boolean iniciar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
        Combate combate = new Combate(heroi, inimigo);
        cartas.inicializacao();
        cartas.comprarAtual();

        heroi.resetarExp();
        heroi.resetarEscudo();
        
        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(interfaceJogo);

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            // A intenção do inimigo foi movida para dentro do executarTurnoJogador
            // para ficar fixa sempre que você for escolher uma carta!
            
            if (heroi.getExp() > 0) {
                executarTurnoJogador(heroi, inimigo, cartas, combate, interfaceJogo);
            } else {
                executarTurnoInimigo(heroi, inimigo, cartas, combate, interfaceJogo);
            }
        }

        boolean venceu = heroi.estaVivo() && !inimigo.estaVivo();
        
        if (venceu) {
            concederRecompensas(heroi, interfaceJogo);
            heroi.resetarEscudo();
            heroi.limparEfeitos();
            interfaceJogo.aguardarEnter("\n\u001B[33m>> Pressione Enter para continuar a jornada... \u001B[m");
        }

        return venceu;
    }

    private void executarTurnoJogador(Heroi heroi, Inimigo inimigo, DequeHeroi cartas, Combate combate, Interface interfaceJogo) {
        // Painel FIXO de intenção do inimigo com espaçamento elegante
        System.out.println("\n\u001B[31m------------------------------------------------------------\u001B[m");
        System.out.println("\u001B[1;31m[!] ALERTA:\u001B[m \u001B[31m" + inimigo.anunciarProximaAcao() + "\u001B[m");
        System.out.println("\u001B[31m------------------------------------------------------------\u001B[m");

        System.out.println("\n\u001B[1;33m>> SUAS CARTAS:\u001B[m\n");
        cartas.mostraAtual();
        System.out.println("\u001B[36m[ 0 ]\u001B[m \u001B[37mEncerrar Turno\u001B[m");
        System.out.println("\n------------------------------------------------------------");

        int opcao = interfaceJogo.leitura(0, 5); 
        if (opcao == 0) {
            heroi.zerarExp();
            return;
        }

        Carta carta = cartas.getAtual().get(opcao - 1);
        if (carta == null) return;

        if (carta.getCusto() > heroi.getExp()) {
            interfaceJogo.atualizaTela(heroi);
            mostrarPainelVs(interfaceJogo);
            System.out.println("\n\u001B[31m[!] Energia (XP) insuficiente para esta carta!\u001B[m");
            return;
        }

        int dado = carta.usar(heroi, inimigo, combate);
        String texto = carta.usarTexto(heroi, inimigo, dado, carta.getNome());
        heroi.gastarExp(carta.getCusto());
        cartas.usar(opcao - 1);

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(interfaceJogo);
        System.out.println("\n\u001B[33m>> " + texto + "\u001B[m");
    }

    private void executarTurnoInimigo(Heroi heroi, Inimigo inimigo, DequeHeroi cartas, Combate combate, Interface interfaceJogo) {
        combate.notificar(EventoCombate.FIM_TURNO_JOGADOR);
        if (!inimigo.estaVivo()) return;

        ResultadoAcaoInimigo res = inimigo.executarTurno(heroi, combate);
        combate.notificar(EventoCombate.FIM_TURNO_INIMIGO);

        heroi.resetarExp();
        heroi.resetarEscudo();
        cartas.descartarAtual();
        cartas.comprarAtual();

        interfaceJogo.atualizaTela(heroi);
        mostrarPainelVs(interfaceJogo);
        System.out.println("\n\u001B[33m>> " + res.getMensagemCombate() + "\u001B[m");
    }

    private void mostrarPainelVs(Interface interfaceJogo) {
        System.out.println(" \u001B[31m[ VS ]\u001B[m\n");
        System.out.println(interfaceJogo.obterArteInimigo(inimigo.getNome()));
        inimigo.atualiza();
    }

    private void concederRecompensas(Heroi heroi, Interface interfaceJogo) {
        Random rand = new Random();
        System.out.println("\n\u001B[32m============================================================");
        System.out.println("|                   VITÓRIA NA BATALHA!                    |");
        System.out.println("============================================================\u001B[m");

        String nome = inimigo.getNome();
        if (nome.equals("Creeper")) {
            int qtd = 1 + rand.nextInt(2);
            heroi.adicionarPolvora(qtd);
            System.out.println("\u001B[33m>> DROP:\u001B[m Você obteve " + qtd + " Pólvora(s)!");
        } else if (nome.contains("Esqueleto")) {
            int qtd = 1 + rand.nextInt(3);
            heroi.adicionarOsso(qtd);
            System.out.println("\u001B[33m>> DROP:\u001B[m Você obteve " + qtd + " Osso(s)!");
        } else if (nome.equals("Evoker") || nome.equals("Bruxa") || nome.equals("Warden")) {
            int qtd = 1;
            heroi.adicionarEsmeralda(qtd);
            System.out.println("\u001B[33m>> DROP:\u001B[m Você obteve " + qtd + " Esmeralda!");
        } else {
            String rec = heroi.recursos();
            System.out.println("\u001B[33m>> DROP:\u001B[m Você encontrou " + rec + "!");
        }
    }
}