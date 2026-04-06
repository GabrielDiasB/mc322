/**
 * Base para efeitos contínuos aplicados em entidades.
 * Implementa o contrato de notificação do sistema de combate.
 */
public abstract class Efeito implements Subscriber {
    /** Nome público do efeito, usado para identificar empilhamento. */
    String nome;
    /** Entidade que possui o efeito. */
    Entidade dono;
    /** Quantidade atual de acúmulos restantes. */
    int acumulos;

    /**
     * Cria um efeito com um dono e um número inicial de acúmulos.
     *
     * @param nome nome do efeito
     * @param dono entidade que recebe o efeito
     * @param acumulos quantidade inicial de acúmulos
     */
    public Efeito(String nome, Entidade dono, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
    }

    /**
     * Retorna o nome do efeito.
     *
     * @return nome do efeito
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna os acúmulos atualmente ativos.
     *
     * @return quantidade atual de acúmulos
     */
    public int getAcumulos() {
        return acumulos;
    }

    /**
     * Soma novos acúmulos ao efeito atual.
     *
     * @param qtd quantidade adicional a ser acumulada
     */
    public void adicionarAcumulos(int qtd) {
        this.acumulos += qtd;
    }

    /**
     * Reduz os acúmulos e remove o efeito quando ele se esgota.
     *
     * @param qtd quantidade a remover dos acúmulos
     * @param combate combate atual, usado para desinscrever o efeito
     */
    public void reduzirAcumulos(int qtd, Combate combate) {
        this.acumulos -= qtd;
        if (this.acumulos <= 0) {
            dono.removerEfeito(this, combate);
        }
    }

    /**
     * Retorna a representação textual do efeito para exibição na interface.
     *
     * @return texto formatado do efeito
     */
    public String getString() {
        return nome + "(+" + acumulos + ")";
    }
}