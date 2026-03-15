import java.util.Random;

public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int vidaInicial;
    private int exp; // energia
    private int expInicial;
    private int escudoInicial;
    private int madeira;
    private int ferro;
    private int diamante;
    private int la;

    public Heroi(String nome, int vida, int escudo, int vidaInicial, int exp, int escudoInicial){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vida;
        this.exp = exp;
        this.expInicial = exp;
        this.escudoInicial = escudoInicial;
        this.madeira = 0;
        this.ferro = 0;
        this.diamante = 0;
        this.la = 0;
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
        return "VIDA: [" + "■".repeat(vida) + "-".repeat(vidaInicial - vida) + "] " + vida + "/" + vidaInicial;
        
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        return "ESCUDO: [" + "■".repeat(escudo) + "-".repeat(escudoInicial - escudo) + "] " + escudo + "/" + escudoInicial;
    }

    public String atualizaXp() {
        if (exp < 0) {
            exp = 0;
        }
        return "XP: [" + "■".repeat(exp) + "-".repeat(expInicial - exp) + "] "+ exp + "/" + expInicial;
    }

    public String inventario() {
        return "\nMadeira: " + madeira + " | Ferro: " + ferro + " | Diamante: " + diamante + " | Lã: " + la;
    }

    public String recursos() {
        Random rand = new Random();
        int num = rand.nextInt(10);

        if (num < 5) {
            madeira++;
            return "1 madeira";
        } else if (num < 7) {
            ferro++;
            return "1 ferro";
        } else if (num < 9) {
            la++;
            return "1 lã";
        } else {
            diamante++;
            return "1 diamante";
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