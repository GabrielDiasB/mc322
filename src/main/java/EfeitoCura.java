/**
 * Efeito instantâneo que recupera os pontos de vida do herói.
 */
public class EfeitoCura extends Efeito {

    /**
     * Cria um efeito de Cura e já recupera a vida do alvo instantaneamente.
     *
     * @param dono entidade que será curada
     * @param acumulos quantidade de vida a ser recuperada
     */
    public EfeitoCura(Entidade dono, int acumulos) {
        super("Cura", dono, acumulos);
        dono.recuperar(acumulos); // Aplica a cura imediatamente usando o novo método!
    }

    @Override
    public String serNotificado(EventoCombate evento, Combate combate) {
        // A Cura é instantânea, não precisa interagir com turnos subsequentes.
        return "";
    }
}