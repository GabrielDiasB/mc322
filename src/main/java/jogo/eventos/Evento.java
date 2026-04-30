package jogo.eventos;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;

/**
 * Classe abstrata que representa qualquer nó do mapa (Batalhas, Acampamentos, Escolhas).
 */
public abstract class Evento {
    /**
     * Executa o evento e altera o estado do herói.
     * @return true se o herói sobreviveu, false caso tenha morrido no evento.
     */
    public abstract boolean iniciar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo);
}
