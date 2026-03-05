public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int vidaInicial;

    public Heroi(String nome, int vida, int escudo, int vidaInicial){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vida;
    }

    public String receberDano() {
        vida -= 2;
        return nome + " recebeu 2 de dano! " + "Vida: " + vida + "/" + vidaInicial;
    }

    public String ganharEscudo() {
        escudo++;
        return "Ganhei escudo! Escudo: " + escudo;
    }

    public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }
}