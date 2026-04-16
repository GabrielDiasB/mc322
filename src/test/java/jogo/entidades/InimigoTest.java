package jogo.entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import jogo.batalha.*;

public class InimigoTest {

    private AcaoInimigo[] createAcoes() {
        AcaoInimigo[] acoes = new AcaoInimigo[3];
        acoes[0] = new AcaoInimigo("Ataque", TipoAcaoInimigo.Ataque, 10, 80, 50);
        acoes[1] = new AcaoInimigo("Defesa", TipoAcaoInimigo.Defesa, 5, 60, 30);
        acoes[2] = new AcaoInimigo("Buff", TipoAcaoInimigo.Buff, 0, 100, 20);
        return acoes;
    }

    @Test
    public void testConstructor() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        assertEquals("Goblin", inimigo.getNome());
        assertEquals(50, inimigo.getVida());
        assertEquals(20, inimigo.getEscudo());
        assertEquals(50, inimigo.getVidaInicial());
        assertEquals(20, inimigo.getEscudoInicial());
    }

    @Test
    public void testGetVida() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        // Test normal case
        assertEquals(50, inimigo.getVida());

        // Test with damage that exceeds shield
        inimigo.receberDano(25);  // 20 shield + 5 life damage
        assertEquals(45, inimigo.getVida());
    }

    @Test
    public void testReceberDano() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        // Damage less than shield - no life damage, shield absorbs all
        int damageTaken = inimigo.receberDano(10);
        assertEquals(0, damageTaken);  // no damage to life
        assertEquals(50, inimigo.getVida());
        assertEquals(10, inimigo.getEscudo());

        // Damage equal to shield - no life damage, shield depleted
        damageTaken = inimigo.receberDano(10);
        assertEquals(0, damageTaken);  // no damage to life
        assertEquals(50, inimigo.getVida());
        assertEquals(0, inimigo.getEscudo());

        // Damage more than shield - life takes the excess
        damageTaken = inimigo.receberDano(20);
        assertEquals(20, damageTaken);  // damage to life
        assertEquals(30, inimigo.getVida());
        assertEquals(0, inimigo.getEscudo());
    }

    @Test
    public void testAtualizaVida() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        String vidaStr = inimigo.atualizaVida();
        assertTrue(vidaStr.contains("50"));
    }

    @Test
    public void testAtualizaEscudo() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        String escudoStr = inimigo.atualizaEscudo();
        assertTrue(escudoStr.contains("20"));
    }

    @Test
    public void testAtualiza() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        // Test that it doesn't throw exception
        inimigo.atualiza();
        // No assertion needed, just check it runs
    }

    @Test
    public void testAnunciarProximaAcao() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);

        String anuncio = inimigo.anunciarProximaAcao();
        assertNotNull(anuncio);
        assertTrue(anuncio.contains("Ataque") || anuncio.contains("Defesa") || anuncio.contains("Buff"));
    }

    @Test
    public void testExecutarTurno() {
        AcaoInimigo[] acoes = createAcoes();
        Inimigo inimigo = new Inimigo("Goblin", 50, 20, acoes[0], acoes[1], acoes[2]);
        Heroi heroi = new Heroi("Hero", 100, 10, 10);
        Combate combate = new Combate(heroi, inimigo);

        ResultadoAcaoInimigo resultado = inimigo.executarTurno(heroi, combate);
        assertNotNull(resultado);
        assertTrue(resultado.getNomeAcao().equals("Ataque") || resultado.getNomeAcao().equals("Defesa") || resultado.getNomeAcao().equals("Buff"));
    }
}