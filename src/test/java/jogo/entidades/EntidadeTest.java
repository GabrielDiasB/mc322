package jogo.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntidadeTest {

    @Test
    void testConstructor() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals("Teste", entidade.getNome());
        assertEquals(100, entidade.getVida());
        assertEquals(50, entidade.getEscudo());
        assertEquals(100, entidade.getVidaInicial());
        assertEquals(50, entidade.getEscudoInicial());
    }

    @Test
    void testGetVida() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals(100, entidade.getVida());
    }

    @Test
    void testGetEscudo() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals(50, entidade.getEscudo());
    }

    @Test
    void testGetVidaInicial() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals(100, entidade.getVidaInicial());
    }

    @Test
    void testGetEscudoInicial() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals(50, entidade.getEscudoInicial());
    }

    @Test
    void testGetNome() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertEquals("Teste", entidade.getNome());
    }

    @Test
    void testGetEfeitos() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertNotNull(entidade.getEfeitos());
        assertTrue(entidade.getEfeitos().isEmpty());
    }

    @Test
    void testRecuperar() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        entidade.recuperar(10);
        assertEquals(100, entidade.getVida()); // capped at vidaInicial
    }

    @Test
    void testEstaVivo() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        assertTrue(entidade.estaVivo());
        // To test false, need to damage, but since receberDano is not tested yet, skip for now
    }

    @Test
    void testReceberDano() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        int dano = entidade.receberDano(30);
        assertEquals(0, dano); // escudo 50 >= 30, so no damage to vida
        assertEquals(100, entidade.getVida()); // vida unchanged
        assertEquals(20, entidade.getEscudo()); // 50 - 30 = 20
    }

    @Test
    void testAtualizaVida() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        String result = entidade.atualizaVida();
        assertNotNull(result);
    }

    @Test
    void testAtualizaEscudo() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        String result = entidade.atualizaEscudo();
        assertNotNull(result);
    }

    @Test
    void testGetBonusDano() {
        Heroi entidade = new Heroi("Teste", 100, 50, 20);
        int bonus = entidade.getBonusDano();
        assertEquals(0, bonus); // No effects, so 0
    }
}