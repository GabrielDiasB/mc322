public class CartaDano extends Carta {

    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }


    public int usar(Heroi heroi, Inimigo inimigo) {
        return inimigo.receberDano(dano);
    }


    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        if (dado > 0) {
            return (heroi.getNome() + " usou " + nome + "! " + inimigo.getNome() + " levou " + dado + " de dano.");
        } else {
            return (heroi.getNome() + " usou " + nome + "! "+ inimigo.getNome() + " bloqueou o ataque com o escudo!");
        }
        
    }

}

