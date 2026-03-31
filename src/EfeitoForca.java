public class EfeitoForca extends Efeito {
    public EfeitoForca(Entidade dono, int acumulos) {
        super("Força", dono, acumulos);
    }

    @Override
    public String serNotificado(EventoCombate evento, Combate combate) {
        if (evento == EventoCombate.ATAQUE_JOGADOR) {
            return (" " + dono.getNome() + " ataca com " + acumulos + " de dano fixo!");
        }
        return "";
    }
}