public class ResultadoAcaoInimigo {
    private String nomeAcao;
    private boolean acertou;
    private int danoAplicado;
    private int escudoGanho;
    private String mensagemCombate;

    public ResultadoAcaoInimigo(String nomeAcao, boolean acertou, int danoAplicado, int escudoGanho, String mensagemCombate) {
        this.nomeAcao = nomeAcao;
        this.acertou = acertou;
        this.danoAplicado = danoAplicado;
        this.escudoGanho = escudoGanho;
        this.mensagemCombate = mensagemCombate;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public boolean isAcertou() {
        return acertou;
    }

    public int getDanoAplicado() {
        return danoAplicado;
    }

    public int getEscudoGanho() {
        return escudoGanho;
    }

    public String getMensagemCombate() {
        return mensagemCombate;
    }
}
