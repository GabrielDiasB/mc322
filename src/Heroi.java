public class Heroi extends Entidade {

    private int exp; // energia
    private int expInicial;
    

    public Heroi(String nome, int vida, int escudo, int exp){
        super(nome, vida, escudo, vida, escudo);
        this.exp = exp;
        this.expInicial = exp;

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
        System.out.println(getNome() + " " + "\u001B[32m" + atualizaVida() + "\u001B[m | " + "\u001B[33m" + atualizaEscudo() + "\u001B[m" + "\u001B[m | " + "\u001B[35m" + atualizaXp() + "\u001B[m");
    }

    public void titulo() {
        System.out.println("\u001B[1;36m=-=-=-=-= CRAFT & COMBATE =-=-=-=-=\u001B[m\n");
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
}
