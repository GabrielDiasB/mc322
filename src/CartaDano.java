public class CartaDano extends Carta {

    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }


    public int usar(Heroi heroi, Inimigo inimigo) {
        return inimigo.receberDano(dano);
    }

}

