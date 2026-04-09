package jogo.servicos;
import jogo.batalha.*;
import jogo.entidades.Inimigo;


/**
 * Fabrica de inimigos por fase da campanha.
 */
public final class CriaInimigos {
    /** Classe utilitaria sem instancias. */
    private CriaInimigos() {
    }

    /**
     * Cria um inimigo para a fase informada com sorteio entre opcoes da fase.
     *
     * @param fase indice da fase (0 a 2)
     * @return inimigo sorteado da fase, ou null para fase invalida
     */
    public static Inimigo gerarInimigoDaFase(int fase) {
        int sorteio = (int) (Math.random() * 4);

        if (fase == 0) {
            if (sorteio == 0) {
                return new Inimigo(
                    "Zumbi",
                    20,
                    2,
                    new AcaoInimigo("Arranhão", TipoAcaoInimigo.Ataque, 4, 85, 50),
                    new AcaoInimigo("Mordida", TipoAcaoInimigo.Ataque, 7, 60, 25),
                    new AcaoInimigo("Carne Podre", TipoAcaoInimigo.Debuff, 2, 90, 25)
                );
            } else if (sorteio == 1) {
                return new Inimigo(
                    "Esqueleto",
                    10,
                    1,
                    new AcaoInimigo("Flecha rápida", TipoAcaoInimigo.Ataque, 5, 90, 50),
                    new AcaoInimigo("Postura defensiva", TipoAcaoInimigo.Defesa, 1, 100, 25),
                    new AcaoInimigo("Focar Mira", TipoAcaoInimigo.Buff, 1, 100, 25)
                );
            } else if (sorteio == 2) {
                return new Inimigo(
                    "Creeper",
                    20,
                    0,
                    new AcaoInimigo("Investida explosiva", TipoAcaoInimigo.Ataque, 9, 45, 35),
                    new AcaoInimigo("Estouro curto", TipoAcaoInimigo.Ataque, 5, 85, 50),
                    new AcaoInimigo("Carapaça de poeira", TipoAcaoInimigo.Defesa, 1, 100, 15)
                );
            }
            return new Inimigo(
                "Aranha das Cavernas",
                15,
                1,
                new AcaoInimigo("Salto", TipoAcaoInimigo.Ataque, 5, 90, 40),
                new AcaoInimigo("Picada Venenosa", TipoAcaoInimigo.Debuff, 3, 85, 40),
                new AcaoInimigo("Teia Protetora", TipoAcaoInimigo.Defesa, 2, 100, 20)
            );
        }

        if (fase == 1) {
            if (sorteio == 0) {
                return new Inimigo(
                    "Blaze",
                    25,
                    3,
                    new AcaoInimigo("Rajada de fogo", TipoAcaoInimigo.Ataque, 9, 75, 55),
                    new AcaoInimigo("Chama intensa", TipoAcaoInimigo.Ataque, 12, 50, 30),
                    new AcaoInimigo("Aura flamejante", TipoAcaoInimigo.Defesa, 2, 100, 15)
                );
            } else if (sorteio == 1) {
                return new Inimigo(
                    "Enderman",
                    30,
                    1,
                    new AcaoInimigo("Golpe teleportado", TipoAcaoInimigo.Ataque, 10, 70, 50),
                    new AcaoInimigo("Soco sombrio", TipoAcaoInimigo.Ataque, 13, 50, 30),
                    new AcaoInimigo("Fúria do End", TipoAcaoInimigo.Buff, 2, 100, 20)
                );
            } else if (sorteio == 2) {
                return new Inimigo(
                    "Esqueleto Wither",
                    15,
                    8,
                    new AcaoInimigo("Golpe sombrio", TipoAcaoInimigo.Ataque, 10, 80, 50),
                    new AcaoInimigo("Corte perfurante", TipoAcaoInimigo.Ataque, 14, 45, 20),
                    new AcaoInimigo("Toque do Wither", TipoAcaoInimigo.Debuff, 2, 90, 30)
                );
            }
            return new Inimigo(
                "Bruxa",
                25,
                2,
                new AcaoInimigo("Poção de Dano", TipoAcaoInimigo.Ataque, 8, 90, 30),
                new AcaoInimigo("Arremessar Veneno", TipoAcaoInimigo.Debuff, 4, 85, 40),
                new AcaoInimigo("Poção de Força", TipoAcaoInimigo.Buff, 2, 100, 30)
            );
        }

        if (fase == 2) {
            if (sorteio == 0) {
                return new Inimigo(
                    "Wither",
                    30,
                    8,
                    new AcaoInimigo("Cabeçada de wither", TipoAcaoInimigo.Ataque, 12, 78, 40),
                    new AcaoInimigo("Rajada devastadora", TipoAcaoInimigo.Ataque, 16, 48, 25),
                    new AcaoInimigo("Névoa de Decomposição", TipoAcaoInimigo.Debuff, 4, 90, 35)
                );
            } else if (sorteio == 1) {
                return new Inimigo(
                    "Warden",
                    40,
                    5,
                    new AcaoInimigo("Soco tectônico", TipoAcaoInimigo.Ataque, 14, 75, 50),
                    new AcaoInimigo("Impacto ensurdecedor", TipoAcaoInimigo.Ataque, 18, 42, 25),
                    new AcaoInimigo("Fúria Cega", TipoAcaoInimigo.Buff, 3, 100, 25)
                );
            } else if (sorteio == 2) {
                return new Inimigo(
                    "Guardião",
                    30,
                    5,
                    new AcaoInimigo("Garra aquática", TipoAcaoInimigo.Ataque, 11, 82, 55),
                    new AcaoInimigo("Corrente profunda", TipoAcaoInimigo.Ataque, 15, 50, 25),
                    new AcaoInimigo("Escama ancestral", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            }
            return new Inimigo(
                "Evoker",
                25,
                5,
                new AcaoInimigo("Presas de Ferro", TipoAcaoInimigo.Ataque, 13, 85, 40),
                new AcaoInimigo("Totem de Poder", TipoAcaoInimigo.Buff, 2, 100, 30),
                new AcaoInimigo("Maldição do Illager", TipoAcaoInimigo.Debuff, 3, 90, 30)
            );
        }

        return null;
    }

    /**
     * Cria o chefe final da campanha.
     *
     * @return inimigo chefe final
     */
    public static Inimigo criarBossFinal() {
        return new Inimigo(
            "Ender Dragon",
            50,
            10,
            new AcaoInimigo("Sopro dracônico", TipoAcaoInimigo.Ataque, 20, 65, 35),
            new AcaoInimigo("Garras do vazio", TipoAcaoInimigo.Ataque, 14, 85, 25),
            new AcaoInimigo("Rugido de Poder", TipoAcaoInimigo.Buff, 2, 100, 20),
            new AcaoInimigo("Bafo Venenoso", TipoAcaoInimigo.Debuff, 4, 80, 20)
        );
    }
}
