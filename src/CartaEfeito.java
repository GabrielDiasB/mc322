public class CartaEfeito extends Carta {
    private String tipoEfeito;
    private int quantidadeEfeito;

    public CartaEfeito(String nome, String descricao, int custo, int quantidadeEfeito, String tipoEfeito) { 
        super(nome, descricao, custo); 
        this.quantidadeEfeito = quantidadeEfeito;
        this.tipoEfeito = tipoEfeito;
    }

    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        if(tipoEfeito.equals("Veneno")) {
            inimigo.aplicarEfeito(new EfeitoVeneno(inimigo, quantidadeEfeito), combate);
        } else if (tipoEfeito.equals("Forca")) {
            heroi.aplicarEfeito(new EfeitoForca(heroi, quantidadeEfeito), combate);
        }
        return quantidadeEfeito;
    }

    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        return heroi.getNome() + " usou " + nome + "!";
    }
}