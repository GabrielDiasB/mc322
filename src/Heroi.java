public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int vidaInicial;
    private int exp; // energia
    private int expInicial;
    private int escudoInicial;

    public Heroi(String nome, int vida, int escudo, int vidaInicial, int exp, int escudoInicial){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vida;
        this.exp = exp;
        this.expInicial = exp;
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

    public int getEscudoInicial() {
        return escudoInicial;
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

    public String atualizaXp() {
        if (exp < 0) {
            exp = 0;
        }
        if(exp == expInicial) {
            return "XP: [■■■■■■■■■■] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 90){
            return "XP: [■■■■■■■■■-] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 80) {
            return "XP: [■■■■■■■■--] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 70) {
            return "XP: [■■■■■■■---] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 60) {
            return "XP: [■■■■■■----] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 50) {
            return "XP: [■■■■■-----] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 40) {
            return "XP: [■■■■------] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 30) {
            return "XP: [■■■-------] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 20) {
            return "XP: [■■--------] " + exp + "/" + expInicial;
        } else if (100 * exp/expInicial >= 10) {
            return "XP: [■---------] " + exp + "/" + expInicial;
        } else {
            return "XP: [----------] " + exp + "/" + expInicial;
        } 
    }

    public void atualiza() {
        System.out.println(getNome() + " " + "\u001B[32m" + atualizaVida() + "\u001B[m | " + "\u001B[34m" + atualizaEscudo() + "\u001B[m" + "\u001B[m | " + "\u001B[35m" + atualizaXp() + "\u001B[m");
    }

    public void titulo() {
        System.out.println("\u001B[1;36m=-=-=-=-= CRAFT & COMBATE =-=-=-=-=\u001B[m\n");
    }

    public int receberDano(int danoSofrido) {
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            System.out.println(nome + " recebeu " + danoReal + " de dano!" );
        }
        return danoReal;
    }

    public void receberEscudo(int escudoRecebido){
        escudo += escudoRecebido;
        System.out.println("Recebi +" + escudoRecebido + " de escudo!");
    }

    public void gastarExp(int custo) {
        exp -= custo;
    }

    public void resetarExp() {
        exp = expInicial;
    }

    public void resetarEscudo() {
        escudo = 0;
    }

    public void zerarExp() {
        exp = 0;
    }


    public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }
}