public class CartaDano {
    private String nome;
    private int custoExp;
    private int dano;

    public CartaDano(String nome, int custoExp, int dano) {
        this.nome = nome;
        this.custoExp = custoExp;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoExp() {
        return custoExp;
    }

    public int usar(){
        return dano;
    }



}

