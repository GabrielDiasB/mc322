package jogo.batalha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jogo.entidades.Inimigo;

class DequeInimigoTest {

    @Test
    void deveComecarVazioERetornarNullAoRemover() {
        DequeInimigo deque = new DequeInimigo();

        assertTrue(deque.vazio());
        assertEquals(0, deque.getTamanho());
        assertNull(deque.removerDoInicio());
    }

    @Test
    void deveManterOrdemFifoNasRemocoes() {
        DequeInimigo deque = new DequeInimigo();
        Inimigo primeiro = criarInimigo("Primeiro");
        Inimigo segundo = criarInimigo("Segundo");

        deque.adicionaNoFim(primeiro);
        deque.adicionaNoFim(segundo);

        assertFalse(deque.vazio());
        assertEquals(2, deque.getTamanho());
        assertSame(primeiro, deque.removerDoInicio());
        assertEquals(1, deque.getTamanho());
        assertSame(segundo, deque.removerDoInicio());
        assertEquals(0, deque.getTamanho());
        assertTrue(deque.vazio());
        assertNull(deque.removerDoInicio());
    }

    private Inimigo criarInimigo(String nome) {
        return new Inimigo(
            nome,
            10,
            0,
            new AcaoInimigo("Golpe", TipoAcaoInimigo.Ataque, 2, 100, 100),
            new AcaoInimigo("Bloqueio", TipoAcaoInimigo.Defesa, 1, 100, 0),
            new AcaoInimigo("Grito", TipoAcaoInimigo.Buff, 1, 100, 0)
        );
    }
}