package jogo.entidades;
import jogo.batalha.*;
import jogo.efeitos.*;


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
     * Cria uma entidade com estado inicial de vida e escudo.
     *
     * @param nome nome da entidade
     * @param vida vida atual da entidade
     * @param escudo escudo atual da entidade
     * @param vidaInicial valor base de vida máxima
     * @param escudoInicial valor base de escudo máximo
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
     * Retorna a vida atual da entidade, limitada ao mínimo de zero.
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
     * Retorna o escudo atual da entidade.
     *
     * @return escudo atual
     */
    public int getEscudo() {
        return escudo;
    }

    /**
     * Retorna a vida inicial configurada para a entidade.
     *
     * @return vida inicial
     */
    public int getVidaInicial() {
        return vidaInicial;
    }

    /**
     * Retorna o escudo inicial configurado para a entidade.
     *
     * @return escudo inicial
     */
    public int getEscudoInicial() {
        return escudoInicial;
    }

    /**
     * Retorna a lista de efeitos ativos da entidade.
     *
     * @return efeitos atualmente ativos
     */
    public List<Efeito> getEfeitos() {
        return efeitos;
    }

    /**
     * Recupera pontos de vida da entidade sem ultrapassar a vida inicial.
     *
     * @param cura quantidade de vida recuperada
     */
    public void recuperar(int cura) {
        this.vida += cura;
        if (this.vida > this.vidaInicial) {
            this.vida = this.vidaInicial;
        }
    }

    /**
     * Varre a lista de efeitos ativos em busca do efeito "Força" ou "Forca"
     * e retorna a quantidade de acúmulos para somar no dano.
     *
     * @return dano extra baseado nos acúmulos de força
     */
    public int getBonusDano() {
        int bonus = 0;
        for (Efeito e : efeitos) {
            if (e.getNome().equals("Força") || e.getNome().equals("Forca")) {
                bonus += e.getAcumulos();
            }
        }
        return bonus;
    }

    /**
     * Gera a barra textual de vida no formato de interface.
     *
     * @return string com barra e valores de vida
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
     * Gera a barra textual de escudo no formato de interface.
     *
     * @return string com barra e valores de escudo
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
     * Aplica dano recebido, consumindo primeiro o valor de escudo.
     *
     * @param danoSofrido valor bruto de dano recebido
     * @return dano efetivo causado na vida
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
     * Indica se a entidade ainda possui vida positiva.
     *
     * @return true se a entidade está viva
     */
    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Adiciona ou acumula um efeito contínuo na entidade.
     *
     * @param novoEfeito efeito a ser aplicado
     * @param combate contexto de combate para inscrição do efeito
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
     * Remove um efeito contínuo da entidade e do combate.
     *
     * @param efeito efeito a ser removido
     * @param combate contexto de combate para desinscrição
     */
    public void removerEfeito(Efeito efeito, Combate combate) {
        efeitos.remove(efeito);
        combate.desinscrever(efeito);
    }

    /**
     * Limpa todos os efeitos ativos da entidade.
     */
    public void limparEfeitos() {
        this.efeitos.clear();
    }
}