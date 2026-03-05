public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int ataque;

    public Inimigo(String nome, int vida, int escudo, int ataque){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.ataque = ataque;
    }
    public void receberDano(int danoSofrido){
        this.vida -= danoSofrido;
    }


    public int atacar(){
        return ataque;

    }


    public boolean estaVivo(){
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
