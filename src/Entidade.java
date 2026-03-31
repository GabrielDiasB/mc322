import java.util.ArrayList;
import java.util.List;

public abstract class Entidade {
    String nome;
    int vida;
    int escudo;    
    int vidaInicial;
    int escudoInicial;
    List<Efeito> efeitos;

    public Entidade(String nome, int vida, int escudo, int vidaInicial, int escudoInicial) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
        this.escudoInicial = escudoInicial;
        this.efeitos = new ArrayList<>();
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

    public String atualizaVida() {
        if (vida < 0) {
            vida = 0;
        }
        if (vidaInicial <= 0) {
            return "VIDA: [----------] " + vida + "/" + vidaInicial;
        }

        int quadrados = (vida * 10) / vidaInicial;
        if (quadrados < 0) {
            quadrados = 0;
        }
        if (quadrados > 10) {
            quadrados = 10;
        }

        return "VIDA: [" + "■".repeat(quadrados) + "-".repeat(10 - quadrados) + "] " + vida + "/" + vidaInicial;
        
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        if (escudoInicial <= 0 || escudo > escudoInicial) {
            return "ESCUDO: [" + "■".repeat(escudo) + "] " + escudo;
        }
        return "ESCUDO: [" + "■".repeat(escudo) + "-".repeat(escudoInicial - escudo) + "] " + escudo + "/" + escudoInicial;
    }

    public int receberDano(int danoSofrido) {
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            return danoReal;
        }
        return 0;
    }

    public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void aplicarEfeito(Efeito novoEfeito, Combate combate) {
        for (Efeito e : efeitos) {
            if (e.getNome().equals(novoEfeito.getNome())) {
                e.adicionarAcumulos(novoEfeito.getAcumulos());
                return;
            }
        }
        efeitos.add(novoEfeito);
        combate.inscrever(novoEfeito);
    }

    public void removerEfeito(Efeito efeito, Combate combate) {
        efeitos.remove(efeito);
        combate.desinscrever(efeito);
    }

    public void limparEfeitos() {
        this.efeitos.clear();
    }
}
