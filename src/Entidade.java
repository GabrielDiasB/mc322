abstract class Entidade {
    String nome;
    int vida;
    int escudo;    
    int vidaInicial;
    int escudoInicial;

    public Entidade(String nome, int vida, int escudo, int vidaInicial, int escudoInicial) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
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

    public int getEscudoInicial() {
        return escudoInicial;
    }

    public String atualizaVida(){
        if (vida < 0) {
            vida = 0;
        }
        if (vidaInicial <= 0) {
            return "VIDA: [----------] " + vida + "/" + vidaInicial;
        }

        int quadradosPreenchidos = (vida * 10) / vidaInicial;
        if (quadradosPreenchidos < 0) {
            quadradosPreenchidos = 0;
        }
        if (quadradosPreenchidos > 10) {
            quadradosPreenchidos = 10;
        }

        return "VIDA: [" + "■".repeat(quadradosPreenchidos) + "-".repeat(10 - quadradosPreenchidos) + "] " + vida + "/" + vidaInicial;
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        if (escudoInicial <= 0 || escudo > escudoInicial) {
            return "ESCUDO: [" + "■".repeat(escudo) + "] " + escudo;
        }
        if (escudo == escudoInicial) {
            return "ESCUDO: [" + "■".repeat(escudo) + "] " + escudo + "/" + escudoInicial;
        } else {
            return "ESCUDO: [" + "■".repeat(escudo) + "-".repeat(escudoInicial - escudo) + "] " + escudo + "/" + escudoInicial;
        }
    }

        public int receberDano(int danoSofrido){
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            System.out.println(nome + " recebeu " + danoReal + " de dano!" );
            return danoReal;
        } else {
            System.out.println(nome + " bloqueou o ataque com o escudo!");
            return 0;
        }

    }

        public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
