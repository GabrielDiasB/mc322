import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class DequeHeroi {
    private List<Carta> cartas;
    private List<Carta> atual;
    private List<Carta> descarte;
    private List<Carta> deque;

    public DequeHeroi() {
        this.cartas = new ArrayList<>();
        this.atual = new ArrayList<>();
        this.descarte = new ArrayList<>();
        this.deque = new ArrayList<>();
    }

    public void addCarta(Carta carta) {
        cartas.add(carta);
    }

    public void inicializacao() {
        deque = new ArrayList<>(cartas);
        Collections.shuffle(deque);
        atual.clear();
        descarte.clear();
    }

    public void comprarCarta() {
        if (deque.isEmpty()) {
            if (descarte.isEmpty()) {
                System.out.printf("Todas as cartas foram compradas!");
                return;
            }

            deque.addAll(descarte);
            descarte.clear();
            Collections.shuffle(deque);
        } 

        Carta comprada = deque.remove(0);
        atual.add(comprada);
    }

    public void comprarAtual() {
        for (int i = 0; i < 5; i++) {
            comprarCarta();
        }
    }

    public void usar(int pos) {
        if (pos >=0 && pos < atual.size()) {
            Carta usada = atual.get(pos);
            if (usada != null) {
                descarte.add(usada);
                atual.set(pos, null);
            } else {
                System.out.println("Já usou essa carta!");
            }
        } else {
            System.out.println("Essa carta não existe!");
        }
    }

    public void descartarAtual() {
        for (int i = 0; i < atual.size(); i++) {
            Carta carta = atual.get(i);
            if (carta != null) {
                descarte.add(carta);
            }
        }
        atual.clear();
    }

    public void mostraAtual() {
        for (int i = 0; i < atual.size(); i++) {
            Carta carta = atual.get(i);
            if (carta == null) {
                System.out.println("");
            } else {
                System.out.println("[ " + (i + 1) + " ] " + String.format("%-17s", carta.getNome()) + "==> " + String.format("%-12s",carta.getDescricao()) + "-" + carta.getCusto() + "XP");
            }
            
        }
        
    }

    public void mostraCartas() {
        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = cartas.get(i);
            
            System.out.println("  - " + String.format("%-17s", carta.getNome()) + "==> " + String.format("%-12s",carta.getDescricao()) + "-" + carta.getCusto() + "XP");
        }
        
    }

    public List<Carta> getAtual() {
        return atual;
    }

}
