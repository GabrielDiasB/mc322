public class AcaoInimigo {
    private String nome;
    private TipoAcaoInimigo tipo;
    private int valor;
    private int precisao;
    private int chanceEscolha;

    public AcaoInimigo(String nome, TipoAcaoInimigo tipo, int valor, int precisao, int chanceEscolha) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.precisao = precisao;
        this.chanceEscolha = chanceEscolha;
    }

    public String getNome() {
        return nome;
    }

    public TipoAcaoInimigo getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public int getPrecisao() {
        return precisao;
    }

    public int getChanceEscolha() {
        return chanceEscolha;
    }
}
