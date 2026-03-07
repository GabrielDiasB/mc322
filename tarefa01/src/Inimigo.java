public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int ataque;
    private int vidaInicial;

    public Inimigo(String nome, int vida, int escudo, int ataque){
        this.nome = nome;
        this.vida = vida;
        this.vidaInicial = vida;
        this.escudo = escudo;
        this.ataque = ataque;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public void receberDano(int danoSofrido){
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            System.out.println(nome + " recebeu " + danoReal + " de dano!" );
        }
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
