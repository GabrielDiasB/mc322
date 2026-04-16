package jogo.mapa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapaCampanhaTest {

    @Test
    public void mapaPadraoDeveOferecerDoisCaminhosAPartirDaRaiz() {
        MapaCampanha mapa = MapaCampanha.criarPadrao();

        assertEquals(2, mapa.getRaiz().getProximosNaoVisitados().size());
    }

    @Test
    public void deveExistirCaminhoAteNoFinal() {
        MapaCampanha mapa = MapaCampanha.criarPadrao();
        NoMapa raiz = mapa.getRaiz();

        List<NoMapa> fila = new ArrayList<>();
        fila.add(raiz);

        boolean encontrouFinal = false;
        for (int i = 0; i < fila.size(); i++) {
            NoMapa atual = fila.get(i);
            if (atual.ehFinal()) {
                encontrouFinal = true;
                break;
            }
            fila.addAll(atual.getProximosNaoVisitados());
        }

        assertTrue(encontrouFinal);
    }
}
