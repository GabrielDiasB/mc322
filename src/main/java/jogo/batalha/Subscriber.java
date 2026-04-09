package jogo.batalha;


/**
 * Contrato para objetos que reagem a eventos de combate.
 */
public interface Subscriber {
    /**
     * Recebe uma notificação do combate e devolve a mensagem a ser exibida.
     *
     * @param evento evento disparado pelo combate
     * @param combate combate que originou o evento
     * @return texto gerado pela reação ao evento
     */
    String serNotificado(EventoCombate evento, Combate combate);
}