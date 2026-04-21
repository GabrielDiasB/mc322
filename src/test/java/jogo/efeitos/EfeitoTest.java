package jogo.efeitos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jogo.batalha.AcaoInimigo;
import jogo.batalha.Combate;
import jogo.batalha.EventoCombate;
import jogo.batalha.TipoAcaoInimigo;
import jogo.entidades.Heroi;
import jogo.entidades.Inimigo;

class EfeitoTest {

    @Test
    void deveManipularAcumulosERemoverQuandoZerar() {
        Heroi heroi = new Heroi("Alex", 30, 0, 5);
        Combate combate = criarCombateCom(heroi);
        EfeitoForca forca = new EfeitoForca(heroi, 2);

        heroi.aplicarEfeito(forca, combate);
        assertEquals("Força", forca.getNome());
        assertEquals(2, forca.getAcumulos());
        assertEquals("Força(+2)", forca.getString());

        forca.adicionarAcumulos(3);
        assertEquals(5, forca.getAcumulos());

        forca.reduzirAcumulos(5, combate);
        assertTrue(heroi.getEfeitos().isEmpty());
    }

    @Test
    void efeitoForcaDeveNotificarSomenteNoAtaqueDoJogador() {
        Heroi heroi = new Heroi("Alex", 30, 0, 5);
        EfeitoForca forca = new EfeitoForca(heroi, 3);
        Combate combate = criarCombateCom(heroi);

        String msgAtaque = forca.serNotificado(EventoCombate.ATAQUE_JOGADOR, combate);
        String msgOutroEvento = forca.serNotificado(EventoCombate.FIM_TURNO_JOGADOR, combate);

        assertTrue(msgAtaque.contains("FORÇA"));
        assertEquals("", msgOutroEvento);
    }

    @Test
    void efeitoCuraDeveCurarInstantaneamente() {
        Heroi heroi = new Heroi("Alex", 50, 0, 5);
        heroi.receberDano(20);
        assertEquals(30, heroi.getVida());

        EfeitoCura cura = new EfeitoCura(heroi, 10);

        assertEquals(40, heroi.getVida());
        assertEquals("", cura.serNotificado(EventoCombate.ATAQUE_INIMIGO, criarCombateCom(heroi)));
    }

    @Test
    void efeitoVenenoDeveCausarDanoEExpirar() {
        Heroi heroi = new Heroi("Alex", 40, 0, 5);
        Combate combate = criarCombateCom(heroi);
        EfeitoVeneno veneno = new EfeitoVeneno(heroi, 2);
        heroi.aplicarEfeito(veneno, combate);

        String msg1 = veneno.serNotificado(EventoCombate.FIM_TURNO_JOGADOR, combate);
        assertTrue(msg1.contains("Veneno"));
        assertEquals(38, heroi.getVida());
        assertEquals(1, veneno.getAcumulos());

        String msg2 = veneno.serNotificado(EventoCombate.FIM_TURNO_JOGADOR, combate);
        assertTrue(msg2.contains("Veneno"));
        assertEquals(37, heroi.getVida());
        assertEquals(0, veneno.getAcumulos());
        assertTrue(heroi.getEfeitos().isEmpty());
    }

    @Test
    void efeitoVenenoNaoDeveAtuarEmEventoInvalido() {
        Heroi heroi = new Heroi("Alex", 40, 0, 5);
        Combate combate = criarCombateCom(heroi);
        EfeitoVeneno veneno = new EfeitoVeneno(heroi, 3);

        int vidaAntes = heroi.getVida();
        String msg = veneno.serNotificado(EventoCombate.ATAQUE_JOGADOR, combate);

        assertEquals("", msg);
        assertEquals(vidaAntes, heroi.getVida());
    }

    private Combate criarCombateCom(Heroi heroi) {
        Inimigo inimigo = new Inimigo(
            "Zombie",
            20,
            0,
            new AcaoInimigo("Golpe", TipoAcaoInimigo.Ataque, 2, 100, 100),
            new AcaoInimigo("Def", TipoAcaoInimigo.Defesa, 1, 100, 0),
            new AcaoInimigo("Buf", TipoAcaoInimigo.Buff, 1, 100, 0)
        );
        return new Combate(heroi, inimigo);
    }
}