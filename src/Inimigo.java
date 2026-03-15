public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int ataque;
    private int vidaInicial;
    private int escudoInicial;

    public Inimigo(String nome, int vida, int escudo, int ataque, int escudoInicial){
        this.nome = nome;
        this.vida = vida;
        this.vidaInicial = vida;
        this.escudo = escudo;
        this.ataque = ataque;
        this.escudoInicial = escudoInicial;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int receberDano(int danoSofrido){
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            System.out.println(nome + " recebeu " + danoReal + " de dano!" );
        }
        return danoReal;
    }

    public String atualizaVida() {
        if (vida < 0) {
            vida = 0;
        } 
        return "VIDA: [" + "■".repeat(vida) + "-".repeat(vidaInicial - vida) + "] " + vida + "/" + vidaInicial;
        
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        return "ESCUDO: [" + "■".repeat(escudo) + "-".repeat(escudoInicial - escudo) + "] " + escudo + "/" + escudoInicial;
    }

    public void atualiza() {
        System.out.println("\u001B[31m" + getNome() + " " + atualizaVida() + "\u001B[m | " + "\u001B[31m" + atualizaEscudo() + "\u001B[m\n");
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
