import java.util.ArrayList;
import java.util.List;

public class Combate {
    private Heroi heroi;
    private Inimigo inimigo;
    private List<Subscriber> subscribers;

    public Combate(Heroi heroi, Inimigo inimigo) {
        this.subscribers = new ArrayList<>();
        this.heroi = heroi;
        this.inimigo = inimigo;
    }

    public void inscrever(Subscriber s) {
        if (!subscribers.contains(s)) {
            subscribers.add(s);
        }
    }

    public void desinscrever(Subscriber s) {
        subscribers.remove(s);
    }

    public String notificar(EventoCombate evento) {
        String notificacao = "";
        List<Subscriber> copia = new ArrayList<>(subscribers);
        for (Subscriber s : copia) {
            notificacao += s.serNotificado(evento, this);
        }
        return notificacao;
    }
    
}
