public abstract class Efeito implements Subscriber {
    String nome;
    Entidade dono;
    int acumulos;

    public Efeito(String nome, Entidade dono, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
    }

    public String getNome() {
        return nome;
    }

    public int getAcumulos() {
        return acumulos;
    }

    public void adicionarAcumulos(int qtd) {
        this.acumulos += qtd;
    }

    public void reduzirAcumulos(int qtd, Combate combate) {
        this.acumulos -= qtd;
        if (this.acumulos <= 0) {
            dono.removerEfeito(this, combate);
        }
    }

    public String getString() {
        return nome + "(+" + acumulos + ")";
    }
}