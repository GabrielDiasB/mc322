/**
 * Resultado resumido da ação executada por um inimigo no turno.
 */
public class ResultadoAcaoInimigo {
    /** Nome da ação executada. */
    private String nomeAcao;
    /** Indica se a ação acertou o alvo. */
    private boolean acertou;
    /** Dano real aplicado pela ação. */
    private int danoAplicado;
    /** Escudo ganho com a ação, quando aplicável. */
    private int escudoGanho;
    /** Mensagem pronta para exibição na interface. */
    private String mensagemCombate;

    /**
     * Cria um resultado consolidado para uma ação do inimigo.
     *
     * @param nomeAcao nome da ação executada
     * @param acertou {@code true} se a ação atingiu o alvo
     * @param danoAplicado dano efetivamente causado
     * @param escudoGanho escudo obtido pela ação
     * @param mensagemCombate mensagem pronta para exibição
     */
    public ResultadoAcaoInimigo(String nomeAcao, boolean acertou, int danoAplicado, int escudoGanho, String mensagemCombate) {
        this.nomeAcao = nomeAcao;
        this.acertou = acertou;
        this.danoAplicado = danoAplicado;
        this.escudoGanho = escudoGanho;
        this.mensagemCombate = mensagemCombate;
    }

    /**
     * Retorna o nome da ação executada.
     *
     * @return nome da ação
     */
    public String getNomeAcao() {
        return nomeAcao;
    }

    /**
     * Retorna {@code true} se a ação acertou.
     *
     * @return resultado do acerto
     */
    public boolean isAcertou() {
        return acertou;
    }

    /**
     * Retorna o dano aplicado pela ação.
     *
     * @return dano aplicado
     */
    public int getDanoAplicado() {
        return danoAplicado;
    }

    /**
     * Retorna o escudo ganho pela ação.
     *
     * @return escudo ganho
     */
    public int getEscudoGanho() {
        return escudoGanho;
    }

    /**
     * Retorna a mensagem textual da ação.
     *
     * @return mensagem de combate
     */
    public String getMensagemCombate() {
        return mensagemCombate;
    }
}
