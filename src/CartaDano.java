public class CartaDano extends Carta {

    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }


    public int usar(Heroi heroi, Inimigo inimigo) {
        return inimigo.receberDano(dano);
    }


    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado) {
        if (dado > 0) {
            return (heroi.getNome() + " usou a carta de dano! " + inimigo.getNome() + " levou " + dado + " de dano.\n");
        } else {
            return (heroi.getNome() + " usou a carta de dano! " + inimigo.getNome() + " bloqueou o ataque com o escudo!\n");
        }
        
    }

}

