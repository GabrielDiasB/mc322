package jogo.entidades;
import jogo.batalha.*;
import jogo.efeitos.*;

import java.util.Random;

public class Heroi extends Entidade {
    private int exp;
    private int expInicial;
    private int madeira, ferro, diamante, la;
    private int polvora, osso, esmeralda;

    public Heroi(String nome, int vida, int escudo, int exp){
        super(nome, vida, escudo, vida, escudo);
        this.exp = exp;
        this.expInicial = exp;
        this.madeira = 0; this.ferro = 0; this.diamante = 0; this.la = 0;
        this.polvora = 0; this.osso = 0; this.esmeralda = 0;
    }

    public int getExp() { return exp; }
    public int getExpInicial() { return expInicial; }

    public void adicionarPolvora(int qtd) { this.polvora += qtd; }
    public void adicionarOsso(int qtd) { this.osso += qtd; }
    public void adicionarEsmeralda(int qtd) { this.esmeralda += qtd; }
    public void gastarEsmeralda(int qtd) { this.esmeralda -= qtd; }
    public boolean temEsmeralda(int n) { return esmeralda >= n; }
    public boolean temPolvora(int n) { return polvora >= n; }
    public boolean temOsso(int n) { return osso >= n; }
    public void gastarPolvora(int n) { polvora -= n; }
    public void gastarOsso(int n) { osso -= n; }

    public String atualizaXp() {
        if (exp < 0) exp = 0;
        return "XP: [" + "■".repeat(exp) + "-".repeat(expInicial - exp) + "] " + exp + "/" + expInicial;
    }

    public String inventario() {
        return "\u001B[1;33m>> INVENTÁRIO:\u001B[m\n" +
               "   \u001B[37m[ Recursos ]\u001B[m Madeira: " + madeira + " | Ferro: " + ferro + " | Lã: " + la + " | Diamante: " + diamante + "\n" +
               "   \u001B[37m[  Drops   ]\u001B[m Pólvora: " + polvora + " | Osso: "  + osso  + " | Esmeralda: " + esmeralda;
    }

    public String recursos() {
        Random rand = new Random();
        int num = rand.nextInt(10);
        if (num < 5) { madeira++; return "1 Madeira"; }
        else if (num < 7) { ferro++; return "1 Ferro"; }
        else if (num < 9) { la++; return "1 Lã"; }
        else { diamante++; return "1 Diamante"; }
    }

    public void receberEscudo(int escudoRecebido){ escudo += escudoRecebido; }
    public void gastarExp(int custo) { exp -= custo; }
    public void resetarExp() { exp = expInicial; }
    public void zerarExp() { exp = 0; }
    public void resetarEscudo() { this.escudo = 0; }
    public void limparEfeitos() { this.efeitos.clear(); }

    public String atualizaVidaBarra() {
        int blocos = (vida * 10) / vidaInicial;
        if (blocos < 0) blocos = 0;
        return "[" + "■".repeat(blocos) + "-".repeat(10 - blocos) + "] " + vida + " HP";
    }

    public String atualizaEscudoBarra() {
        int maxEscudoVisual = 30;
        int blocos = (escudo * 10) / maxEscudoVisual;
        if (blocos > 10) blocos = 10;
        if (blocos < 0) blocos = 0;
        return "[" + "■".repeat(blocos) + "-".repeat(10 - blocos) + "] " + escudo + " DEF";
    }

    public String atualizaXpBarra() {
        return "[" + "■".repeat(exp) + "-".repeat(expInicial - exp) + "] " + exp + "/" + expInicial + " XP";
    }

    public void atualiza() {
        String statusEfeitos = efeitos.isEmpty() ? "Nenhum" : "";
        for (Efeito e : efeitos) { statusEfeitos += e.getString() + " "; }
        
        System.out.print("\u001B[1;36m" + nome.toUpperCase() + "\u001B[m | ");
        System.out.print("\u001B[32mVIDA " + atualizaVidaBarra() + "\u001B[m | ");
        System.out.print("\u001B[34mESCUDO " + atualizaEscudoBarra() + "\u001B[m | ");
        System.out.print("\u001B[35m" + atualizaXpBarra() + "\u001B[m | ");
        System.out.println("EFEITOS: " + statusEfeitos);
    }

    public void gastarMadeira(int c) { madeira -= c; }
    public void gastarLa(int c) { la -= c; }
    public void gastarFerro(int c) { ferro -= c; }
    public void gastarDiamante(int c) { diamante -= c; }
    public boolean temMadeira(int n) { return madeira >= n; }
    public boolean temLa(int n) { return la >= n; }
    public boolean temFerro(int n) { return ferro >= n; }
    public boolean temDiamante(int n) { return diamante >= n; }
    public void recuperar(int n) { super.recuperar(n); }
    public void expProgresso(int dia){ expInicial += dia; }
}