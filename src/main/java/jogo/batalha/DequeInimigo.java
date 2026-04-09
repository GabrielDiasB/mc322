package jogo.batalha;
import jogo.entidades.*;


/**
 * Estrutura simples de fila para organizar os inimigos enfrentados na jornada.
 */
public class DequeInimigo {
    /** Nó interno da lista duplamente encadeada. */
    private static class No {
        Inimigo valor;
        No anterior;
        No proximo;

        No (Inimigo valor) {
            this.valor = valor;
        }
    }

    private No inicio;
    private No fim;
    private int tamanho;

    /** Cria uma fila vazia de inimigos. */
    public DequeInimigo() {
    }

    /**
     * Retorna {@code true} quando a fila está vazia.
     *
     * @return {@code true} se a fila estiver vazia
     */
    public boolean vazio() {
        return tamanho == 0;
    }

    /**
     * Retorna a quantidade de inimigos armazenados na fila.
     *
     * @return tamanho atual da fila
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Adiciona um inimigo ao final da fila.
     *
     * @param inimigo inimigo que será colocado na fila
     */
    public void adicionaNoFim(Inimigo inimigo){
        No novo = new No(inimigo);

        if (vazio()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        }
        tamanho++;
    }

    /**
     * Remove e retorna o primeiro inimigo da fila.
     *
     * @return inimigo removido, ou {@code null} se a fila estiver vazia
     */
    public Inimigo removerDoInicio(){
        if (vazio()) {
            return null;
        }
        Inimigo inimigoRemovido = inicio.valor;
        inicio = inicio.proximo;

        if (inicio != null) {
            inicio.anterior = null;
        } else {
            fim = null;
        }
        tamanho--;
        return inimigoRemovido;
    }

}