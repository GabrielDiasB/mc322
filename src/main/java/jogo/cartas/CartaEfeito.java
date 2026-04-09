package jogo.cartas;
import jogo.batalha.*;
import jogo.entidades.*;
import jogo.efeitos.*;


/**
 * Carta que aplica um efeito contínuo no herói ou no inimigo.
 * No jogo atual, essa carta representa Força, Veneno ou Cura.
 */
public class CartaEfeito extends Carta {
    /** Tipo textual do efeito que será aplicado. */
    private String tipoEfeito;
    /** Quantidade inicial de acúmulos do efeito. */
    private int quantidadeEfeito;

    /**
     * Cria uma carta de efeito contínuo.
     *
     * @param nome nome da carta
     * @param descricao descrição exibida ao jogador
     * @param custo custo em XP para usar a carta
     * @param quantidadeEfeito intensidade inicial do efeito
     * @param tipoEfeito nome textual do efeito a ser aplicado
     */
    public CartaEfeito(String nome, String descricao, int custo, int quantidadeEfeito, String tipoEfeito) { 
        super(nome, descricao, custo); 
        this.quantidadeEfeito = quantidadeEfeito;
        this.tipoEfeito = tipoEfeito;
    }

    /**
     * Aplica o efeito correspondente ao tipo configurado na carta.
     *
     * @param heroi herói do combate
     * @param inimigo inimigo do combate
     * @param combate contexto do combate para inscrição de efeitos
     * @return quantidade do efeito aplicada
     */
    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        if(tipoEfeito.equals("Veneno")) {
            inimigo.aplicarEfeito(new EfeitoVeneno(inimigo, quantidadeEfeito), combate);
        } else if (tipoEfeito.equals("Forca")) {
            heroi.aplicarEfeito(new EfeitoForca(heroi, quantidadeEfeito), combate);
        } else if (tipoEfeito.equals("Cura")) {
            // Aqui a mágica acontece: quando for cura, aplica o EfeitoCura no herói!
            heroi.aplicarEfeito(new EfeitoCura(heroi, quantidadeEfeito), combate);
        }
        return quantidadeEfeito;
    }

    /**
     * Monta a mensagem simples exibida após o uso da carta de efeito.
     *
     * @param heroi herói que usou a carta
     * @param inimigo inimigo alvo, quando houver
     * @param dado valor retornado pelo uso da carta
     * @param nome nome da carta usada
     * @return texto descritivo da ação
     */
    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        return heroi.getNome() + " usou " + nome + "!";
    }
}