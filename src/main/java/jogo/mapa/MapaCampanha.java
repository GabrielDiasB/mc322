package jogo.mapa;

import jogo.batalha.Batalha;
import jogo.eventos.*;
import jogo.servicos.CriaInimigos;
import jogo.servicos.Craft;

public class MapaCampanha {
    private final NoMapa raiz;

    public MapaCampanha(NoMapa raiz) {
        this.raiz = raiz;
    }

    public static MapaCampanha criarPadrao(Craft servicoCraft) {
        NoMapa inicio = new NoMapa("Vilarejo Inicial", 0, false, null);

        // NÍVEL 1: 3 Opções de caminhos iniciais
        NoMapa floresta = new NoMapa("Floresta Densa (Batalha)", 1, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(0)));
        NoMapa caverna = new NoMapa("Caverna Escura (Batalha)", 1, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(0)));
        NoMapa pantano = new NoMapa("Pântano Nebuloso (Batalha)", 1, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(0)));

        // NÍVEL 2: Preparação
        NoMapa acamp1 = new NoMapa("Acampamento na Colina (Fogueira)", 2, false, () -> new Acampamento(servicoCraft));
        NoMapa villager1 = new NoMapa("Mercado Ambulante (Villager)", 2, false, () -> new ComercianteVillager());

        // NÍVEL 3: 3 Opções de caminhos médios
        NoMapa deserto = new NoMapa("Dunas do Deserto (Batalha Forte)", 3, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(1)));
        NoMapa mina = new NoMapa("Mina Abandonada (Batalha Forte)", 3, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(1)));
        NoMapa selva = new NoMapa("Ruínas da Selva (Batalha Forte)", 3, false, () -> new Batalha(CriaInimigos.gerarInimigoDaFase(1)));

        // NÍVEL 4: Preparação Final
        NoMapa acampFinal = new NoMapa("Última Fogueira (Acampamento)", 4, false, () -> new Acampamento(servicoCraft));
        NoMapa villagerFinal = new NoMapa("Tenda do Mestre Villager (Trocas)", 4, false, () -> new ComercianteVillager());

        // NÍVEL 5: Final
        NoMapa boss = new NoMapa("O Portal do End", 5, true, () -> new Batalha(CriaInimigos.criarBossFinal()));

        // CONEXÕES
        inicio.conectar(floresta);
        inicio.conectar(caverna);
        inicio.conectar(pantano);

        floresta.conectar(acamp1); floresta.conectar(villager1);
        caverna.conectar(acamp1); caverna.conectar(villager1);
        pantano.conectar(acamp1); pantano.conectar(villager1);

        acamp1.conectar(deserto); acamp1.conectar(mina); acamp1.conectar(selva);
        villager1.conectar(deserto); villager1.conectar(mina); villager1.conectar(selva);

        deserto.conectar(acampFinal); deserto.conectar(villagerFinal);
        mina.conectar(acampFinal); mina.conectar(villagerFinal);
        selva.conectar(acampFinal); selva.conectar(villagerFinal);

        acampFinal.conectar(boss);
        villagerFinal.conectar(boss);

        return new MapaCampanha(inicio);
    }

    public NoMapa getRaiz() { return raiz; }
}