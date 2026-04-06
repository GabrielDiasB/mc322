/**
 * Carta que causa dano direto ao inimigo.
 * O dano final pode ser aumentado por efeitos de Força ativos no herói.
 */
public class CartaDano extends Carta {

    private int dano;

    /**
     * Cria uma carta de dano com o valor base a ser aplicado no alvo.
     *
     * @param nome nome da carta
     * @param descricao descrição exibida ao jogador
     * @param custo custo em XP para usar a carta
     * @param dano dano base causado pela carta
     */
    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }


    /**
     * Aplica o dano base somado à Força ativa do herói.
     *
     * @param heroi herói que executa a carta
     * @param inimigo inimigo que receberá o dano
     * @param combate contexto atual do combate
     * @return dano realmente aplicado após a defesa do inimigo
     */
    public int usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        int forca = 0;
        for (Efeito e : heroi.efeitos) {
            if (e.getNome().equals("Força")) {
                forca = e.getAcumulos();
            }
        }

        int danoTotal = this.dano + forca;
        combate.notificar(EventoCombate.ATAQUE_JOGADOR);
        return inimigo.receberDano(danoTotal);
    }


    /**
     * Monta a mensagem de resultado do ataque.
     *
     * @param heroi herói que usou a carta
     * @param inimigo alvo do ataque
     * @param dado dano real causado
     * @param nome nome da carta usada
     * @return texto descritivo da ação
     */
    public String usarTexto(Heroi heroi, Inimigo inimigo, int dado, String nome) {
        if (dado > 0) {
            return (heroi.getNome() + " usou " + nome + "! " + inimigo.getNome() + " levou " + dado + " de dano.");
        } else {
            return (heroi.getNome() + " usou " + nome + "! "+ inimigo.getNome() + " bloqueou o ataque com o escudo!");
        }
        
    }

}
