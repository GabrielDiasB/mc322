package jogo.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {

    @Test
    public void testHeroiCreation() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertEquals("TestHero", heroi.getNome());
        assertEquals(100, heroi.getVida());
        assertEquals(20, heroi.getEscudo());
        assertEquals(50, heroi.getExp());
    }

    @Test
    public void testGetExp() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertEquals(50, heroi.getExp());
    }

    @Test
    public void testGetExpInicial() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertEquals(50, heroi.getExpInicial());
    }

    @Test
    public void testTemMadeira() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertFalse(heroi.temMadeira(1));
        // Assuming we can add madeira, but since it's private, perhaps not.
        // The method is temMadeira, which checks if madeira >= amount
        // Since initial is 0, assertFalse
    }

    @Test
    public void testTemLa() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertFalse(heroi.temLa(1));
    }

    @Test
    public void testTemFerro() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertFalse(heroi.temFerro(1));
    }

    @Test
    public void testTemDiamante() {
        Heroi heroi = new Heroi("TestHero", 100, 20, 50);
        assertFalse(heroi.temDiamante(1));
    }
}