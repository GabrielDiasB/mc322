import java.util.ArrayList;
import java.util.List;

/**
 * Base compartilhada entre herói e inimigo para vida, escudo e efeitos ativos.
 */
public abstract class Entidade {
    String nome;
    int vida;
    int escudo;    
    int vidaInicial;
    int escudoInicial;
    /** Lista de efeitos contínuos atualmente ativos nesta entidade. */
    List<Efeito> efeitos;

    /**
     * Cria uma entidade com valores iniciais de vida e escudo.
     *
     * @param nome nome da entidade
     * @param vida vida atual
     * @param escudo escudo atual
     * @param vidaInicial referência máxima usada para exibição da barra de vida
     * @param escudoInicial referência máxima usada para exibição da barra de escudo
     */
    public Entidade(String nome, int vida, int escudo, int vidaInicial, int escudoInicial) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
        this.escudoInicial = escudoInicial;
        this.efeitos = new ArrayList<>();
    }

    /**
     * Retorna o nome da entidade.
     *
     * @return nome da entidade
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a vida atual, normalizada para não ficar negativa.
     *
     * @return vida atual
     */
    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }
    /**
     * Retorna o escudo atual.
     *
     * @return escudo atual
     */
    public int getEscudo() {
        return escudo;
    }

    /**
     * Retorna o valor de referência usado na barra de vida.
     *
     * @return vida inicial
     */
    public int getVidaInicial() {
        return vidaInicial;
    }

    /**
     * Retorna o valor de referência usado na barra de escudo.
     *
     * @return escudo inicial
     */
    public int getEscudoInicial() {
        return escudoInicial;
    }

    /**
     * Formata a barra de vida exibida no terminal.
     *
     * @return barra textual com a vida atual e a vida inicial
     */
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

    /**
     * Formata a barra de escudo exibida no terminal.
     *
     * @return barra textual com o escudo atual e a referência inicial
     */
    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }
        if (escudoInicial <= 0 || escudo > escudoInicial) {
            return "ESCUDO: [" + "■".repeat(escudo) + "] " + escudo;
        }
        return "ESCUDO: [" + "■".repeat(escudo) + "-".repeat(escudoInicial - escudo) + "] " + escudo + "/" + escudoInicial;
    }

    /**
     * Aplica dano considerando o escudo atual como mitigação.
     *
     * @param danoSofrido dano bruto recebido
     * @return dano efetivamente aplicado à vida
     */
    public int receberDano(int danoSofrido) {
        int danoReal = danoSofrido - escudo;
        if (danoReal > 0) {
            this.vida -= danoReal;
            return danoReal;
        }
        return 0;
    }

    /**
     * Retorna {@code true} se a entidade ainda possui vida positiva.
     *
     * @return {@code true} quando a vida é maior que zero
     */
    public boolean estaVivo() {
        if (vida <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adiciona um efeito novo ou acumula um efeito já existente com o mesmo nome.
     *
     * @param novoEfeito efeito a ser aplicado
     * @param combate combate atual, usado para inscrição no sistema de eventos
     */
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

    /**
     * Remove um efeito da entidade e o desinscreve do combate.
     *
     * @param efeito efeito a ser removido
     * @param combate combate atual, usado para remover a inscrição do efeito
     */
    public void removerEfeito(Efeito efeito, Combate combate) {
        efeitos.remove(efeito);
        combate.desinscrever(efeito);
    }

    /** Limpa todos os efeitos ativos da entidade. */
    public void limparEfeitos() {
        this.efeitos.clear();
    }
}
