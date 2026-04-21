package jogo.batalha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jogo.entidades.Heroi;
import jogo.entidades.Inimigo;

class CombateTest {

    @Test
    void deveEvitarInscricaoDuplicadaENotificarUmaVez() {
        Heroi heroi = new Heroi("Jogador", 40, 5, 3);
        Inimigo inimigo = criarInimigo();
        Combate combate = new Combate(heroi, inimigo);

        Subscriber sub = (evento, c) -> "X";
        combate.inscrever(sub);
        combate.inscrever(sub);

        assertEquals("X", combate.notificar(EventoCombate.ATAQUE_JOGADOR));
    }

    @Test
    void deveDesinscreverSubscriber() {
        Heroi heroi = new Heroi("Jogador", 40, 5, 3);
        Inimigo inimigo = criarInimigo();
        Combate combate = new Combate(heroi, inimigo);

        Subscriber sub = (evento, c) -> "Y";
        combate.inscrever(sub);
        combate.desinscrever(sub);

        assertEquals("", combate.notificar(EventoCombate.FIM_TURNO_JOGADOR));
    }

    private Inimigo criarInimigo() {
        return new Inimigo(
            "Dummy",
            20,
            0,
            new AcaoInimigo("Golpe", TipoAcaoInimigo.Ataque, 3, 100, 100),
            new AcaoInimigo("Def", TipoAcaoInimigo.Defesa, 1, 100, 0),
            new AcaoInimigo("Buf", TipoAcaoInimigo.Buff, 1, 100, 0)
        );
    }
}