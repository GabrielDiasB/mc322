package jogo.entidades;
import jogo.batalha.*;
import jogo.efeitos.*;


import java.util.Random;

/**
 * Representa o jogador e concentra XP, inventário de recursos e ações de preparação.
 */
public class Heroi extends Entidade {
    /** XP atual disponível para explorar, descansar e usar cartas. */
    private int exp;
    /** XP máximo exibido na barra; cresce conforme a campanha avança. */
    private int expInicial;
    /** Quantidade de madeira coletada para crafting. */
    private int madeira;
    /** Quantidade de ferro coletada para crafting. */
    private int ferro;
    /** Quantidade de diamante coletada para crafting. */
    private int diamante;
    /** Quantidade de lã coletada para crafting. */
    private int la;

    /**
     * Cria o herói com seus valores iniciais.
     *
     * @param nome nome do personagem do jogador
     * @param vida vida inicial
     * @param escudo escudo inicial
     * @param exp XP inicial
     */
    public Heroi(String nome, int vida, int escudo, int exp){
        super(nome, vida, escudo, vida, escudo);
        this.exp = exp;
        this.expInicial = exp;
        this.madeira = 0;
        this.ferro = 0;
        this.diamante = 0;
        this.la = 0;
    }

    /**
     * Retorna o XP atual do herói.
     *
     * @return XP atual
     */
    public int getExp() {
        return exp;
    }

    /**
     * Retorna o XP máximo usado na barra de progresso.
     *
     * @return XP máximo exibido
     */
    public int getExpInicial() {
        return expInicial;
    }

    /**
     * Formata a barra de XP exibida na interface.
     *
     * @return barra textual de XP
     */
    public String atualizaXp() {
        if (exp < 0) {
            exp = 0;
        }
        return "XP: [" + "■".repeat(exp) + "-".repeat(expInicial - exp) + "] "+ exp + "/" + expInicial;
    }

    /**
     * Retorna o inventário textual com os recursos coletados.
     *
     * @return inventário formatado
     */
    public String inventario() {
        return "\nMadeira: " + madeira + " | Ferro: " + ferro + " | Diamante: " + diamante + " | Lã: " + la + "\n";
    }

    /**
     * Coleta um recurso aleatório para o inventário.
     *
     * @return texto com o recurso obtido na exploração
     */
    public String recursos() {
        Random rand = new Random();
        int num = rand.nextInt(10);

        if (num < 5) {
            madeira++;
            return "1 madeira";
        } else if (num < 7) {
            ferro++;
            return "1 ferro";
        } else if (num < 9) {
            la++;
            return "1 lã";
        } else {
            diamante++;
            return "1 diamante";
        }
    }

    /**
     * Aumenta o escudo do herói.
     *
     * @param escudoRecebido quantidade de escudo a ser adicionada
     */
    public void receberEscudo(int escudoRecebido){
        escudo += escudoRecebido;
    }

    /**
     * Reduz o XP atual do herói.
     *
     * @param custo quantidade de XP a remover
     */
    public void gastarExp(int custo) {
        exp -= custo;
    }

    /** Restaura o XP ao valor máximo atual. */
    public void resetarExp() {
        exp = expInicial;
    }

    /** Zera o escudo do herói no início de certos estados do jogo. */
    public void resetarEscudo() {
        escudo = 0;
    }

    /** Define o XP como zero para encerrar o turno. */
    public void zerarExp() {
        exp = 0;
    }

    /**
     * Consome madeira do inventário.
     *
     * @param custo quantidade a subtrair
     */
    public void gastarMadeira(int custo) {
        madeira -= custo;
    }

    /**
     * Consome lã do inventário.
     *
     * @param custo quantidade a subtrair
     */
    public void gastarLa(int custo) {
        la -= custo;
    }

    /**
     * Consome ferro do inventário.
     *
     * @param custo quantidade a subtrair
     */
    public void gastarFerro(int custo) {
        ferro -= custo;
    }

    /**
     * Consome diamantes do inventário.
     *
     * @param custo quantidade a subtrair
     */
    public void gastarDiamante(int custo) {
        diamante -= custo;
    }

    /**
     * Verifica se existe madeira suficiente para crafting.
     *
     * @param n quantidade necessária
     * @return {@code true} quando há madeira suficiente
     */
    public boolean temMadeira(int n) {
        if (madeira >= n) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe lã suficiente para crafting.
     *
     * @param n quantidade necessária
     * @return {@code true} quando há lã suficiente
     */
    public boolean temLa(int n) {
        if (la >= n) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe ferro suficiente para crafting.
     *
     * @param n quantidade necessária
     * @return {@code true} quando há ferro suficiente
     */
    public boolean temFerro(int n) {
        if (ferro >= n) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe diamante suficiente para crafting.
     *
     * @param n quantidade necessária
     * @return {@code true} quando há diamante suficiente
     */
    public boolean temDiamante(int n) {
        if (diamante >= n) {
            return true;
        }
        return false;
    }

    /**
     * Recupera vida do herói até o teto de 100 pontos.
     *
     * @param n quantidade de vida a recuperar
     */
    public void recuperar(int n) {
        vida += n;
        if (vida > 100) {
            vida = 100;
        }
    }



    /**
     * Aumenta o XP máximo conforme a campanha avança de fase.
     *
     * @param dia índice da batalha atual, usado como incremento gradual
     */
    public void expProgresso(int dia){
        expInicial += dia;
    }

    /** Exibe o estado atual do herói no terminal. */
    public void atualiza() {
        String statusEfeitos = "";
        if (efeitos.isEmpty()) {
            statusEfeitos += "Nenhum\u001B[m";
        } else {
            for (Efeito e : efeitos) {
                statusEfeitos += e.getString() + " ";
            }
        }
        System.out.println(getNome() + " " + "\u001B[32m" + atualizaVida() + "\u001B[m | " + "\u001B[34m" + atualizaEscudo() + "\u001B[m | " + "\u001B[35m" + atualizaXp() + "\u001B[m | EFEITOS: " + statusEfeitos);
    }

}
