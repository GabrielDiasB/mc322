public class Inimigo extends Entidade {
    private int ataque;


    public Inimigo(String nome, int vida, int escudo, int ataque){
        super(nome, vida, escudo, vida, escudo);
        this.ataque = ataque;
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


}
