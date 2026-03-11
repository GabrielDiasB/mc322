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
        if(vida == vidaInicial) {
            return "VIDA: [■■■■■■■■■■] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 90){
            return "VIDA: [■■■■■■■■■-] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 80) {
            return "VIDA: [■■■■■■■■--] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 70) {
            return "VIDA: [■■■■■■■---] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 60) {
            return "VIDA: [■■■■■■----] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 50) {
            return "VIDA: [■■■■■-----] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 40) {
            return "VIDA: [■■■■------] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 30) {
            return "VIDA: [■■■-------] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 20) {
            return "VIDA: [■■--------] " + vida + "/" + vidaInicial;
        } else if (100 * vida/vidaInicial >= 10) {
            return "VIDA: [■---------] " + vida + "/" + vidaInicial;
        } else {
            return "VIDA: [----------] " + vida + "/" + vidaInicial;
        } 
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        if(escudo == escudoInicial) {
            return "ESCUDO: [■■■■■■■■■■] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 90){
            return "ESCUDO: [■■■■■■■■■-] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 80) {
            return "ESCUDO: [■■■■■■■■--] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 70) {
            return "ESCUDO: [■■■■■■■---] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 60) {
            return "ESCUDO: [■■■■■■----] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 50) {
            return "ESCUDO: [■■■■■-----] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 40) {
            return "ESCUDO: [■■■■------] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 30) {
            return "ESCUDO: [■■■-------] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 20) {
            return "ESCUDO: [■■--------] " + escudo + "/" + escudoInicial;
        } else if (100 * escudo/escudoInicial >= 10) {
            return "ESCUDO: [■---------] " + escudo + "/" + escudoInicial;
        } else {
            return "ESCUDO: [----------] " + escudo + "/" + escudoInicial;
        } 
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
