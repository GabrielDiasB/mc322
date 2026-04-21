package jogo.servicos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jogo.entidades.Inimigo;

class CriaInimigosTest {

    @Test
    void deveRetornarNullParaFaseInvalida() {
        assertNull(CriaInimigos.gerarInimigoDaFase(-1));
        assertNull(CriaInimigos.gerarInimigoDaFase(99));
    }

    @Test
    void deveCriarBossFinalComAtributosEsperados() {
        Inimigo boss = CriaInimigos.criarBossFinal();

        assertNotNull(boss);
        assertEquals("Ender Dragon", boss.getNome());
        assertEquals(50, boss.getVida());
        assertEquals(10, boss.getEscudo());
    }

    @Test
    void deveSortearTodosOsInimigosDaFase0() {
        Set<String> esperados = Set.of("Zumbi", "Esqueleto", "Creeper", "Aranha das Cavernas");
        Set<String> encontrados = coletarNomesDaFase(0, esperados, 2000);

        assertEquals(esperados, encontrados);
    }

    @Test
    void deveSortearTodosOsInimigosDaFase1() {
        Set<String> esperados = Set.of("Blaze", "Enderman", "Esqueleto Wither", "Bruxa");
        Set<String> encontrados = coletarNomesDaFase(1, esperados, 2000);

        assertEquals(esperados, encontrados);
    }

    @Test
    void deveSortearTodosOsInimigosDaFase2() {
        Set<String> esperados = Set.of("Wither", "Warden", "Guardião", "Evoker");
        Set<String> encontrados = coletarNomesDaFase(2, esperados, 2000);

        assertEquals(esperados, encontrados);
    }

    private Set<String> coletarNomesDaFase(int fase, Set<String> alvo, int limiteTentativas) {
        Set<String> encontrados = new HashSet<>();

        for (int i = 0; i < limiteTentativas; i++) {
            Inimigo inimigo = CriaInimigos.gerarInimigoDaFase(fase);
            assertNotNull(inimigo);
            encontrados.add(inimigo.getNome());
            if (encontrados.containsAll(alvo)) {
                break;
            }
        }

        return encontrados;
    }
}