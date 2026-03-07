public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int vidaInicial;
    private int exp; // energia
    private int expInicial;

    public Heroi(String nome, int vida, int escudo, int vidaInicial, int exp){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vida;
        this.exp = exp;
        this.expInicial = exp;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }
    public int getEscudo() {
        return escudo;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }
    public int getExp() {
        return exp;
    }
    public int getExpInicial() {
        return expInicial;
    }

    public String receberDano() {
        
        return "\u001B[31m" + nome + " recebeu 2 de dano! " + "\u001B[0m"  + "Vida: " + vida + "/" + vidaInicial ;
    }

    public String ganharEscudo() {
        escudo++;
        return "\u001B[32m" + "Ganhei escudo! "  + "\u001B[0m" + "Escudo: " + escudo;
    }

    public void gastarExp(int custo) {
        exp -= custo;
    }

    public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }
}