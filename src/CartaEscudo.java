public class CartaEscudo extends Carta {
    private int escudo;

    public CartaEscudo(String nome, String descricao, int custo, int escudo) {
        super(nome, descricao, custo);
        this.escudo = escudo;
    }

    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        heroi.receberEscudo(escudo);
        return escudo;
    }

    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        return (heroi.getNome() + " usou " + nome + "! " + "Aumentou em +" + dado + " o escudo");
    }

}

