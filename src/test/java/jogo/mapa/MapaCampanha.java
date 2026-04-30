package jogo.mapa;

import jogo.batalha.Batalha;
import jogo.eventos.*;
import jogo.servicos.CriaInimigos;
import jogo.servicos.Craft;

/**
 * Monta o mapa da campanha integrando diferentes tipos de eventos com múltiplas escolhas.
 */
public class MapaCampanha {
    private final NoMapa raiz;

    public MapaCampanha(NoMapa raiz) {
        this.raiz = raiz;
    }

    public static MapaCampanha criarPadrao(Craft servicoCraft) {
        NoMapa inicio = new NoMapa("Acampamento Inicial", 0, false, null);

        NoMapa trilhaFloresta = new NoMapa("Trilha da Floresta (Batalha)", 1, false, 
            () -> new Batalha(CriaInimigos.gerarInimigoDaFase(0)));
        NoMapa entradaCaverna = new NoMapa("Entrada da Caverna (Batalha)", 1, false, 
            () -> new Batalha(CriaInimigos.gerarInimigoDaFase(0)));

        NoMapa acampamento1 = new NoMapa("Fogueira Escondida (Acampamento)", 2, false, 
            () -> new Acampamento(servicoCraft));
        NoMapa villager1 = new NoMapa("Estrada de Terra (Comerciante Villager)", 2, false, 
            () -> new ComercianteVillager());

        NoMapa piramide = new NoMapa("Pirâmide do Deserto (Batalha Forte)", 3, false, 
            () -> new Batalha(CriaInimigos.gerarInimigoDaFase(1)));
        NoMapa templo = new NoMapa("Templo da Selva (Batalha Forte)", 3, false, 
            () -> new Batalha(CriaInimigos.gerarInimigoDaFase(1)));

        NoMapa acampamentoFinal = new NoMapa("Último Refúgio (Acampamento)", 4, false, 
            () -> new Acampamento(servicoCraft));
        NoMapa villagerFinal = new NoMapa("Mercado Clandestino (Comerciante Villager)", 4, false, 
            () -> new ComercianteVillager());

        NoMapa theEnd = new NoMapa("The End", 5, true, 
            () -> new Batalha(CriaInimigos.criarBossFinal()));

        inicio.conectar(trilhaFloresta);
        inicio.conectar(entradaCaverna);

        trilhaFloresta.conectar(acampamento1);
        trilhaFloresta.conectar(villager1);
        entradaCaverna.conectar(acampamento1);
        entradaCaverna.conectar(villager1);

        acampamento1.conectar(piramide);
        acampamento1.conectar(templo);
        villager1.conectar(piramide);
        villager1.conectar(templo);

        piramide.conectar(acampamentoFinal);
        piramide.conectar(villagerFinal);
        templo.conectar(acampamentoFinal);
        templo.conectar(villagerFinal);

        acampamentoFinal.conectar(theEnd);
        villagerFinal.conectar(theEnd);

        return new MapaCampanha(inicio);
    }

    public NoMapa getRaiz() { return raiz; }
}