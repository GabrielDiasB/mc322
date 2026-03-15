public class Inimigo extends Entidade {
    private int ataque;


    public Inimigo(String nome, int vida, int escudo, int ataque){
        super(nome, vida, escudo, vida, escudo);
        this.ataque = ataque;
    }


    public void atualiza() {
        System.out.println("\u001B[31m" + getNome() + " " + atualizaVida() + "\u001B[m | " + "\u001B[31m" + atualizaEscudo() + "\u001B[m\n");
    }


    public int atacar(){
        return ataque;
    }


}
