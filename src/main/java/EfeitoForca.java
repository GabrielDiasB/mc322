/**
 * Efeito contínuo que aumenta o dano fixo do ataque do herói.
 */
public class EfeitoForca extends Efeito {
    /**
     * Cria um efeito de Força com a quantidade de acúmulos indicada.
     *
     * @param dono entidade que possui a Força
     * @param acumulos quantidade inicial de força
     */
    public EfeitoForca(Entidade dono, int acumulos) {
        super("Força", dono, acumulos);
    }

    /**
     * Produz uma mensagem quando o herói ataca com Força ativa.
     *
     * @param evento evento de combate recebido
     * @param combate combate corrente
     * @return mensagem de texto ou string vazia quando o evento não se aplica
     */
    @Override
    public String serNotificado(EventoCombate evento, Combate combate) {
        if (evento == EventoCombate.ATAQUE_JOGADOR) {
            return (" " + dono.getNome() + " ataca com " + acumulos + " de dano fixo!");
        }
        return "";
    }
}