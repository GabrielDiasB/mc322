package jogo.servicos;
import jogo.cartas.*;
import jogo.entidades.*;
import jogo.interfacejogo.Interface;


/**
 * Fluxo de preparacao diurna: exploracao, descanso e crafting.
 */
public class Craft {
    private final Interface interfaceJogo;

    /**
     * Cria o servico de crafting vinculado a interface do jogo.
     *
     * @param interfaceJogo interface textual do jogo
     */
    public Craft(Interface interfaceJogo) {
        this.interfaceJogo = interfaceJogo;
    }

    /**
     * Adiciona o baralho base inicial do heroi.
     *
     * @param cartas deque do heroi
     */
    public void inicializarBaralhoBase(DequeHeroi cartas) {
        cartas.addCarta(new CartaDano("Chute Feroz", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaDano("Batida Rápida", "+2 Dano", 1, 2));
        cartas.addCarta(new CartaDano("Soco Mortal", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaEscudo("Reflexo Ágil", "+3 Escudo", 1, 3));
        cartas.addCarta(new CartaEscudo("Esquiva Sagaz", "+2 Escudo", 1, 2));
        cartas.addCarta(new CartaEfeito("Poção de Força", "+2 Força", 2, 2, "Forca"));
        cartas.addCarta(new CartaEfeito("Poção de Veneno", "3-1 Dano", 2, 3, "Veneno"));
    }

    /**
     * Executa o menu de preparacao ate o jogador iniciar a noite.
     *
     * @param heroi heroi atual
     * @param cartas baralho e itens de combate do heroi
     */
    public void executarPreparacao(Heroi heroi, DequeHeroi cartas) {
        while (true) {
            System.out.println(
                "\n\u001B[36m[ 1 ]\u001B[m Explorar o mapa (-1XP ==> +1 Recurso)\n" +
                "\u001B[36m[ 2 ]\u001B[m Inventário e Crafts\u001B[m\n" +
                "\u001B[36m[ 3 ]\u001B[m Descansar no acampamento (-1XP ==> +5 vida)\n" +
                "\u001B[36m[ 4 ]\u001B[m Estou pronto para a noite!\n"
            );

            int selecione = interfaceJogo.leitura(1, 4);
            if (selecione == 1) {
                explorar(heroi);
            } else if (selecione == 2) {
                abrirInventarioECrafting(heroi, cartas);
            } else if (selecione == 3) {
                descansar(heroi);
            } else {
                return;
            }
        }
    }

    private void explorar(Heroi heroi) {
        if (heroi.getExp() >= 1) {
            heroi.gastarExp(1);
            interfaceJogo.atualizaTela(heroi);
            interfaceJogo.destaque("\u001B[33m>> " + heroi.getNome() + " explorou e obteve " + heroi.recursos() + "\n");
        } else {
            interfaceJogo.atualizaTela(heroi);
            System.out.println("\u001B[31m>> Você não tem experiência suficiente para ganhar mais recursos.\u001B[m");
        }
        interfaceJogo.destaque(heroi.inventario());
    }

    private void descansar(Heroi heroi) {
        if (heroi.getExp() >= 1) {
            heroi.gastarExp(1);
            heroi.recuperar(5);
            interfaceJogo.atualizaTela(heroi);
            interfaceJogo.destaque(">> " + heroi.getNome() + " descansou e recuperou +5 vida!\n");
        } else {
            interfaceJogo.atualizaTela(heroi);
            interfaceJogo.destaqueErro(">> Você não tem experiência suficiente para recuperar mais vida..\n");
        }
    }

    private void abrirInventarioECrafting(Heroi heroi, DequeHeroi cartas) {
        interfaceJogo.atualizaTela(heroi);
        interfaceJogo.destaque(heroi.inventario());
        System.out.println("\n========================== Inventário ==========================\n");
        cartas.mostraItens();
        System.out.println("\n====================== Craftar Novos Itens ======================\n");

        System.out.println("[ 1 ] " + String.format("%-23s", "Arco e Flecha") + "==> " + String.format("%-12s", "+7 Dano") + String.format("%-20s", "-1 Madeira -1 Lã"));
        System.out.println("[ 2 ] " + String.format("%-23s", "Espada de madeira") + "==> " + String.format("%-12s", "+8 Dano") + String.format("%-20s", "-2 Madeira"));
        System.out.println("[ 3 ] " + String.format("%-23s", "Machado de Ferro") + "==> " + String.format("%-12s", "+15 Dano") + String.format("%-20s", "-1 Madeira -2 Ferro"));
        System.out.println("[ 4 ] " + String.format("%-23s", "Armadura de Diamante") + "==> " + String.format("%-12s", "+9 Escudo") + String.format("%-20s", "-3 Diamante"));
        System.out.println("[ 5 ] " + String.format("%-23s", "Escudo de Ferro") + "==> " + String.format("%-12s", "+5 Escudo") + String.format("%-20s", "-2 Madeira -1 Ferro"));
        System.out.println("[ 6 ] " + String.format("%-23s", "Picareta de Ferro") + "==> " + String.format("%-12s", "+12 Dano") + String.format("%-20s", "-1 Madeira -2 Ferro"));
        System.out.println("[ 7 ] " + String.format("%-23s", "Espada de Diamante") + "==> " + String.format("%-12s", "+20 Dano") + String.format("%-20s", "-1 Madeira -2 Diamante"));
        System.out.println("[ 8 ] " + String.format("%-23s", "Armadura de Ferro") + "==> " + String.format("%-12s", "+7 Escudo") + String.format("%-20s", "-3 Ferro"));
        System.out.println("[ 9 ] " + String.format("%-23s", "Poção de Vida") + "==> " + String.format("%-12s", "+15 Vida") + String.format("%-20s", "-1 Diamante -1 Lã"));
        System.out.println("\n[ 0 ] Voltar para o menu");
        System.out.println("\n=================================================================");

        int opcao = interfaceJogo.leitura(0, 10);
        processarCraft(opcao, heroi, cartas);
    }

    private void processarCraft(int opcao, Heroi heroi, DequeHeroi cartas) {
        if (opcao == 0) {
            interfaceJogo.atualizaTela(heroi);
            return;
        }

        boolean sucesso = false;
        String item = "";

        if (opcao == 1 && heroi.temLa(1) && heroi.temMadeira(1)) {
            heroi.gastarLa(1);
            heroi.gastarMadeira(1);
            cartas.addCarta(new CartaDano("Arco e Flecha", "+7 Dano", 2, 7));
            item = "Arco e Flecha";
            sucesso = true;
        } else if (opcao == 2 && heroi.temMadeira(2)) {
            heroi.gastarMadeira(2);
            cartas.addCarta(new CartaDano("Espada de madeira", "+8 Dano", 2, 8));
            item = "Espada de madeira";
            sucesso = true;
        } else if (opcao == 3 && heroi.temFerro(1) && heroi.temMadeira(1)) {
            heroi.gastarMadeira(1);
            heroi.gastarFerro(1);
            cartas.addCarta(new CartaDano("Machado de Ferro", "+15 Dano", 2, 15));
            item = "Machado de Ferro";
            sucesso = true;
        } else if (opcao == 4 && heroi.temDiamante(3)) {
            heroi.gastarDiamante(3);
            cartas.addCarta(new CartaEscudo("Armadura de Diamante", "+9 Escudo", 2, 9));
            item = "Armadura de Diamante";
            sucesso = true;
        } else if (opcao == 5 && heroi.temFerro(1) && heroi.temMadeira(2)) {
            heroi.gastarFerro(1);
            heroi.gastarMadeira(2);
            cartas.addCarta(new CartaEscudo("Escudo de Ferro", "+5 Escudo", 1, 5));
            item = "Escudo de Ferro";
            sucesso = true;
        } else if (opcao == 6 && heroi.temMadeira(1) && heroi.temFerro(2)) {
            heroi.gastarMadeira(1);
            heroi.gastarFerro(2);
            cartas.addCarta(new CartaDano("Picareta de Ferro", "+12 Dano", 2, 12));
            item = "Picareta de Ferro";
            sucesso = true;
        } else if (opcao == 7 && heroi.temMadeira(1) && heroi.temDiamante(2)) {
            heroi.gastarMadeira(1);
            heroi.gastarDiamante(2);
            cartas.addCarta(new CartaDano("Espada de Diamante", "+20 Dano", 3, 20));
            item = "Espada de Diamante";
            sucesso = true;
        } else if (opcao == 8 && heroi.temFerro(3)) {
            heroi.gastarFerro(3);
            cartas.addCarta(new CartaEscudo("Armadura de Ferro", "+7 Escudo", 2, 7));
            item = "Armadura de Ferro";
            sucesso = true;
        } else if (opcao == 9 && heroi.temDiamante(1) && heroi.temLa(1)) {
            heroi.gastarDiamante(1);
            heroi.gastarLa(1);
            cartas.addCarta(new CartaEfeito("Poção de Vida", "+15 Vida", 2, 15, "Cura"));
            item = "Poção de Vida";
            sucesso = true;
        }

        interfaceJogo.atualizaTela(heroi);
        if (sucesso) {
            interfaceJogo.destaque(">> " + item + " adicionado com sucesso ao seu deque de combate!\n");
        } else {
            interfaceJogo.destaqueErro(">> Recursos insuficientes para craftar, explore mais o mapa!\n");
        }
    }
}
