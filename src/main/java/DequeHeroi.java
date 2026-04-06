import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Gerencia o baralho do herói com compra, mão atual e descarte.
 */
public class DequeHeroi {
    /** Cartas totais disponíveis no baralho do herói. */
    private List<Carta> cartas;
    /** Cartas atualmente na mão do jogador. */
    private List<Carta> atual;
    /** Cartas descartadas durante a batalha atual. */
    private List<Carta> descarte;
    /** Pilha de compra embaralhada. */
    private List<Carta> deque;

    /** Cria uma estrutura vazia de baralho. */
    public DequeHeroi() {
        this.cartas = new ArrayList<>();
        this.atual = new ArrayList<>();
        this.descarte = new ArrayList<>();
        this.deque = new ArrayList<>();
    }

    /**
     * Adiciona uma carta ao conjunto total de cartas do herói.
     *
     * @param carta carta a ser adicionada ao baralho
     */
    public void addCarta(Carta carta) {
        cartas.add(carta);
    }

    /**
     * Reinicia a pilha de compra com o baralho completo e limpa mão e descarte.
     */
    public void inicializacao() {
        deque = new ArrayList<>(cartas);
        Collections.shuffle(deque);
        atual.clear();
        descarte.clear();
    }

    /**
     * Compra uma carta para a mão, reciclando o descarte se a pilha acabar.
     */
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

    /** Compra cinco cartas para iniciar o turno do jogador. */
    public void comprarAtual() {
        for (int i = 0; i < 5; i++) {
            comprarCarta();
        }
    }

    /**
     * Move a carta escolhida da mão para o descarte.
     *
     * @param pos posição da carta na mão atual
     */
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

    /** Descarrega o restante da mão para o descarte ao fim do turno. */
    public void descartarAtual() {
        for (int i = 0; i < atual.size(); i++) {
            Carta carta = atual.get(i);
            if (carta != null) {
                descarte.add(carta);
            }
        }
        atual.clear();
    }

    /** Exibe a mão atual no terminal. */
    public void mostraAtual() {
        for (int i = 0; i < atual.size(); i++) {
            Carta carta = atual.get(i);
            if (carta == null) {
                System.out.println("");
            } else {
                System.out.println("[ " + (i + 1) + " ] " + String.format("%-23s", carta.getNome()) + "==> " + String.format("%-12s",carta.getDescricao()) + "-" + carta.getCusto() + "XP");
            }
            
        }
        
    }

    /** Exibe todas as cartas já cadastradas no baralho. */
    public void mostraCartas() {
        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = cartas.get(i);
            
            System.out.println("  - " + String.format("%-23s", carta.getNome()) + "==> " + String.format("%-12s",carta.getDescricao()) + "-" + carta.getCusto() + "XP");
        }
        
    }

    /** Exibe apenas as cartas craftadas, ignorando o baralho inicial. */
    public void mostraItens() {
        if (cartas.size() == 5) {
            System.out.println("Inventário vazio, você ainda não craftou nenhum item.");
        } else {
            for (int i = 5; i < cartas.size(); i++) {
                Carta carta = cartas.get(i);
                System.out.println("    - " + String.format("%-23s", carta.getNome()) + "==> " + String.format("%-12s",carta.getDescricao()) + "-" + carta.getCusto() + "XP");
            }
        }
        
        
    }

    /**
     * Retorna a lista de cartas atualmente na mão.
     *
     * @return mão atual do jogador
     */
    public List<Carta> getAtual() {
        return atual;
    }

}
