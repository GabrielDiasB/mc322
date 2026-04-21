package jogo.batalha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultadoAcaoInimigoTest {

    @Test
    void deveRetornarCamposConfiguradosNoConstrutor() {
        ResultadoAcaoInimigo resultado = new ResultadoAcaoInimigo("Mordida", true, 7, 0, "Acertou");

        assertEquals("Mordida", resultado.getNomeAcao());
        assertTrue(resultado.isAcertou());
        assertEquals(7, resultado.getDanoAplicado());
        assertEquals(0, resultado.getEscudoGanho());
        assertEquals("Acertou", resultado.getMensagemCombate());
    }
}