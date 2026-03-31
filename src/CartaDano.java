public class CartaDano extends Carta {

    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }


    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        int forca = 0;
        for (Efeito e : heroi.efeitos) {
            if (e.getNome().equals("Força")) {
                forca = e.getAcumulos();
            }
        }

        int danoTotal = this.dano + forca;
        combate.notificar(EventoCombate.ATAQUE_JOGADOR);
        return inimigo.receberDano(danoTotal);
    }


    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        if (dado > 0) {
            return (heroi.getNome() + " usou " + nome + "! " + inimigo.getNome() + " levou " + dado + " de dano.");
        } else {
            return (heroi.getNome() + " usou " + nome + "! "+ inimigo.getNome() + " bloqueou o ataque com o escudo!");
        }
        
    }

}
