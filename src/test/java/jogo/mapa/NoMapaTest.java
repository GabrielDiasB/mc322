package jogo.mapa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoMapaTest {

    @Test
    public void deveListarApenasNosNaoVisitados() {
        NoMapa origem = new NoMapa("Origem", 0, false, null);
        NoMapa a = new NoMapa("A", 1, false, null);
        NoMapa b = new NoMapa("B", 1, false, null);

        origem.conectar(a);
        origem.conectar(b);
        a.marcarVisitado();

        assertEquals(1, origem.getProximosNaoVisitados().size());
        assertEquals("B", origem.getProximosNaoVisitados().get(0).getNome());
    }

    @Test
    public void deveMarcarNoComoVisitado() {
        NoMapa no = new NoMapa("Teste", 1, false, null);
        assertTrue(!no.estaVisitado());

        no.marcarVisitado();

        assertTrue(no.estaVisitado());
    }
}
