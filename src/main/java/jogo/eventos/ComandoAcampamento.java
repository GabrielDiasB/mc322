package jogo.eventos;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;

/**
 * Contrato do padrão Command para as ações possíveis no Acampamento.
 */
public interface ComandoAcampamento {
    void executar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo);
    String getDescricao();
}