/**
 * Representa uma ação possível de um inimigo durante o combate.
 * Cada ação define o nome, o tipo, o valor aplicado, a precisão e o peso na escolha.
 */
public class AcaoInimigo {
    private String nome;
    private TipoAcaoInimigo tipo;
    private int valor;
    private int precisao;
    /** Peso relativo usado quando o inimigo sorteia a próxima ação. */
    private int chanceEscolha;

    /**
     * Cria uma ação com seus parâmetros de combate.
     *
     * @param nome nome exibido da ação
     * @param tipo tipo da ação, como ataque, defesa, buff ou debuff
     * @param valor intensidade do efeito causado pela ação
     * @param precisao chance de a ação acertar, em porcentagem
     * @param chanceEscolha peso relativo da ação na seleção aleatória
     */
    public AcaoInimigo(String nome, TipoAcaoInimigo tipo, int valor, int precisao, int chanceEscolha) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.precisao = precisao;
        this.chanceEscolha = chanceEscolha;
    }

    /**
     * Retorna o nome exibido da ação.
     *
     * @return nome exibido da ação
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o tipo lógico da ação.
     *
     * @return tipo da ação
     */
    public TipoAcaoInimigo getTipo() {
        return tipo;
    }

    /**
     * Retorna o valor numérico aplicado pela ação.
     *
     * @return valor aplicado
     */
    public int getValor() {
        return valor;
    }

    /**
     * Retorna a precisão da ação em porcentagem.
     *
     * @return precisão em porcentagem
     */
    public int getPrecisao() {
        return precisao;
    }

    /**
     * Retorna o peso relativo usado na seleção da próxima ação.
     *
     * @return peso da ação na seleção aleatória
     */
    public int getChanceEscolha() {
        return chanceEscolha;
    }
}
