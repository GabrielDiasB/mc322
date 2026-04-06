/**
 * Efeito contínuo que causa dano no fim do turno e vai se esgotando com o tempo.
 */
public class EfeitoVeneno extends Efeito {
    /**
     * Cria um efeito de Veneno com a quantidade de acúmulos indicada.
     *
     * @param dono entidade envenenada
     * @param acumulos quantidade inicial de veneno
     */
    public EfeitoVeneno(Entidade dono, int acumulos) {
        super("Veneno", dono, acumulos);
    }

    /**
     * Aplica o dano por veneno no fim do turno apropriado.
     *
     * @param evento evento de combate recebido
     * @param combate combate corrente
     * @return mensagem de dano ou string vazia quando o evento não se aplica
     */
    @Override
    public String serNotificado(EventoCombate evento, Combate combate) {
        if ((evento == EventoCombate.FIM_TURNO_JOGADOR && dono instanceof Heroi) ||
            (evento == EventoCombate.FIM_TURNO_INIMIGO && dono instanceof Inimigo)) {
            dono.receberDano(acumulos);
            reduzirAcumulos(1, combate);
            return (" " + dono.getNome() + " sofre " + (acumulos + 1) + " de dano por Veneno!");
        }
        return "";
    }
}