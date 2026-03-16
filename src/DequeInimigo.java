public class DequeInimigo {
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

    public boolean vazio() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

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