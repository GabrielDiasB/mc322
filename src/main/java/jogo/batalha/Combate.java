package jogo.batalha;
import jogo.entidades.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Coordena o combate entre herói e inimigo e distribui eventos para os efeitos ativos.
 */
public class Combate {
    private Heroi heroi;
    private Inimigo inimigo;
    private List<Subscriber> subscribers;

    /**
     * Cria um combate para um herói e um inimigo específicos.
     *
     * @param heroi personagem controlado pelo jogador
     * @param inimigo adversário atual
     */
    public Combate(Heroi heroi, Inimigo inimigo) {
        this.subscribers = new ArrayList<>();
        this.heroi = heroi;
        this.inimigo = inimigo;
    }

    /**
     * Inscreve um observador para receber eventos do combate.
     *
     * @param s observador que será notificado
     */
    public void inscrever(Subscriber s) {
        if (!subscribers.contains(s)) {
            subscribers.add(s);
        }
    }

    /**
     * Remove um observador da lista de notificações.
     *
     * @param s observador a ser removido
     */
    public void desinscrever(Subscriber s) {
        subscribers.remove(s);
    }

    /**
     * Notifica todos os observadores sobre um evento do combate.
     *
     * @param evento evento que ocorreu no turno
     * @return mensagens produzidas pelos observadores ao tratar o evento
     */
    public String notificar(EventoCombate evento) {
        String notificacao = "";
        List<Subscriber> copia = new ArrayList<>(subscribers);
        for (Subscriber s : copia) {
            notificacao += s.serNotificado(evento, this);
        }
        return notificacao;
    }
    
}
