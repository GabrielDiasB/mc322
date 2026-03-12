public class CartaEscudo {
    private String nome;
    private int custoExp;
    private int escudo;

    public CartaEscudo(String nome, int custoExp, int escudo) {
        this.nome = nome;
        this.custoExp = custoExp;
        this.escudo = escudo;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoExp() {
        return custoExp;
    }

    public int usar(){
        return escudo;
    }



}

