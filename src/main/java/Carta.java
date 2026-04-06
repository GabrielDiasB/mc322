/**
 * Base abstrata de todas as cartas do jogo.
 * Cada carta possui custo, nome e descrição, além de uma forma própria de uso.
 */
abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;

    /**
     * Cria uma carta com as informações exibidas na interface.
     *
     * @param nome nome da carta
     * @param descricao descrição curta do efeito da carta
     * @param custo custo em XP para usá-la
     */
    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    /**
     * Retorna o nome da carta.
     *
     * @return nome da carta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o custo em XP da carta.
     *
     * @return custo em XP
     */
    public int getCusto() {
        return custo;
    }

    /**
     * Retorna a descrição resumida do efeito da carta.
     *
     * @return descrição da carta
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Aplica o efeito da carta no combate.
     *
     * @param heroi herói que está usando a carta
     * @param inimigo inimigo alvo da carta, quando aplicável
     * @param combate contexto atual do combate para eventos e efeitos
     * @return valor numérico associado ao efeito, usado para montar a mensagem de uso
     */
    public abstract int usar(Heroi heroi, Inimigo inimigo, Combate combate);

    /**
     * Monta a mensagem textual exibida após o uso da carta.
     *
     * @param heroi herói que usou a carta
     * @param inimigo inimigo afetado pela carta
     * @param dado valor retornado por {@link #usar(Heroi, Inimigo, Combate)}
     * @param nome nome da carta usada
     * @return texto descritivo da ação realizada
     */
    public abstract String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome);
}
