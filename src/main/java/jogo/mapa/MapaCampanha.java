package jogo.mapa;

import jogo.servicos.CriaInimigos;


/**
 * Define o mapa da campanha como uma árvore de nós de batalha.
 */
public class MapaCampanha {
    private final NoMapa raiz;

    /**
     * Cria um mapa com um nó raiz.
     *
     * @param raiz nó inicial da campanha
     */
    public MapaCampanha(NoMapa raiz) {
        this.raiz = raiz;
    }

    /**
     * Monta um mapa padrão com bifurcações e convergência até o chefe final.
     *
     * @return mapa pronto para navegação
     */
    public static MapaCampanha criarPadrao() {
        NoMapa inicio = new NoMapa("Acampamento Inicial", 0, false, null);

        NoMapa trilhaDaFloresta = new NoMapa("Trilha da Floresta", 1, false, () -> CriaInimigos.gerarInimigoDaFase(0));
        NoMapa entradaDaCaverna = new NoMapa("Entrada da Caverna", 1, false, () -> CriaInimigos.gerarInimigoDaFase(0));

        NoMapa piramideDoDeserto = new NoMapa("Piramide do Deserto", 2, false, () -> CriaInimigos.gerarInimigoDaFase(1));
        NoMapa temploDaSelva = new NoMapa("Templo da selva", 2, false, () -> CriaInimigos.gerarInimigoDaFase(1));

        NoMapa fortalezaDoNether = new NoMapa("Fortaleza do Nether", 3, false, () -> CriaInimigos.gerarInimigoDaFase(2));
        NoMapa monumentoOceanico = new NoMapa("Monumento Oceanico", 3, false, () -> CriaInimigos.gerarInimigoDaFase(2));

        NoMapa theEnd = new NoMapa("The End", 4, true, CriaInimigos::criarBossFinal);

        inicio.conectar(trilhaDaFloresta);
        inicio.conectar(entradaDaCaverna);

        trilhaDaFloresta.conectar(piramideDoDeserto);
        trilhaDaFloresta.conectar(temploDaSelva);
        entradaDaCaverna.conectar(piramideDoDeserto);
        entradaDaCaverna.conectar(temploDaSelva);

        piramideDoDeserto.conectar(fortalezaDoNether);
        piramideDoDeserto.conectar(monumentoOceanico);
        temploDaSelva.conectar(fortalezaDoNether);
        temploDaSelva.conectar(monumentoOceanico);

        fortalezaDoNether.conectar(theEnd);
        monumentoOceanico.conectar(theEnd);

        return new MapaCampanha(inicio);
    }

    /**
     * @return nó inicial da campanha
     */
    public NoMapa getRaiz() {
        return raiz;
    }
}