package jogo.cartas;
import jogo.batalha.*;
import jogo.entidades.*;
import jogo.efeitos.*;


/**
 * Carta que aumenta o escudo do herói.
 */
public class CartaEscudo extends Carta {
    /** Valor de escudo concedido pela carta. */
    private int escudo;

    /**
     * Cria uma carta defensiva.
     *
     * @param nome nome da carta
     * @param descricao descrição exibida ao jogador
     * @param custo custo em XP para usar a carta
     * @param escudo quantidade de escudo concedida
     */
    public CartaEscudo(String nome, String descricao, int custo, int escudo) {
        super(nome, descricao, custo);
        this.escudo = escudo;
    }

    /**
     * Aplica escudo diretamente ao herói.
     *
     * @param heroi herói que recebe o escudo
     * @param inimigo inimigo do combate, não utilizado nesta carta
     * @param combate contexto do combate, não utilizado nesta carta
     * @return valor de escudo concedido
     */
    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        heroi.receberEscudo(escudo);
        return escudo;
    }

    /**
     * Monta a mensagem de retorno do uso da carta.
     *
     * @param heroi herói que usou a carta
     * @param inimigo inimigo do combate, não utilizado
     * @param dado valor de escudo concedido
     * @param nome nome da carta usada
     * @return texto descritivo da ação
     */
    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        return (heroi.getNome() + " usou " + nome + "! " + "Aumentou em +" + dado + " o escudo");
    }

}

