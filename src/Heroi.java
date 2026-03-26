import java.util.Random;

public class Heroi extends Entidade {
    private int exp;
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
        return "\nMadeira: " + madeira + " | Ferro: " + ferro + " | Diamante: " + diamante + " | Lã: " + la + "\n";
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

    public void gastarMadeira(int custo) {
        madeira -= custo;
    }

    public void gastarLa(int custo) {
        la -= custo;
    }

    public void gastarFerro(int custo) {
        ferro -= custo;
    }

    public void gastarDiamante(int custo) {
        diamante -= custo;
    }

    public boolean temMadeira(int n) {
        if (madeira >= n) {
            return true;
        }
        return false;
    }

    public boolean temLa(int n) {
        if (la >= n) {
            return true;
        }
        return false;
    }

    public boolean temFerro(int n) {
        if (ferro >= n) {
            return true;
        }
        return false;
    }

    public boolean temDiamante(int n) {
        if (diamante >= n) {
            return true;
        }
        return false;
    }

    public void recuperar(int n) {
        vida += n;
        if (vida > 20) {
            vida = 20;
        }
    }



    public void expProgresso(int dia){
        expInicial += dia;
    }
}
