import java.util.Random;

public class Heroi extends Entidade {
    private int exp; // energia
    private int expInicial;
    private int madeira;
    private int ferro;
    private int diamante;
    private int la;

    public Heroi(String nome, int vida, int escudo, int exp){
        super(nome, vida, escudo, vida, escudo);
        this.exp = exp;
        this.expInicial = exp;
        this.madeira = 0;
        this.ferro = 0;
        this.diamante = 0;
        this.la = 0;
    }

    public int getExp() {
        return exp;
    }

    public int getExpInicial() {
        return expInicial;
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

    public void receberEscudo(int escudoRecebido){
        escudo += escudoRecebido;
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
}
