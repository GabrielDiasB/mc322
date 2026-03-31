public class EfeitoVeneno extends Efeito {
    public EfeitoVeneno(Entidade dono, int acumulos) {
        super("Veneno", dono, acumulos);
    }

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