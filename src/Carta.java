abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;

    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract int usar(Heroi heroi, Inimigo inimigo, Combate combate);

    public abstract String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome);
}
