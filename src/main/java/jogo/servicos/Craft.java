package jogo.servicos;
import jogo.cartas.*;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;

/**
 * Serviço de Loja/Crafting do jogo.
 */
public class Craft {
    private final Interface interfaceJogo;

    public Craft(Interface interfaceJogo) {
        this.interfaceJogo = interfaceJogo;
    }

    public void inicializarBaralhoBase(DequeHeroi cartas) {
        cartas.addCarta(new CartaDano("Chute Feroz", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaDano("Batida Rápida", "+2 Dano", 1, 2));
        cartas.addCarta(new CartaDano("Soco Mortal", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaEscudo("Reflexo Ágil", "+3 Escudo", 1, 3));
        cartas.addCarta(new CartaEscudo("Esquiva Sagaz", "+2 Escudo", 1, 2));
        cartas.addCarta(new CartaEfeito("Poção de Força", "+2 Força", 2, 2, "Forca"));
        cartas.addCarta(new CartaEfeito("Poção de Veneno", "3-1 Dano", 2, 3, "Veneno"));
    }

    public void abrirMesa(Heroi heroi, DequeHeroi cartas) {
        while (true) {
            interfaceJogo.limpar();
            System.out.println("\u001B[36m============================================================\u001B[m");
            System.out.println("\u001B[1;36m|                     MESA DE CRAFTING                     |\u001B[m");
            System.out.println("\u001B[36m============================================================\u001B[m");
            System.out.println(heroi.inventario() + "\n");
            
            System.out.println("\u001B[33m>> Receitas Disponíveis para Criação:\u001B[m\n");
            System.out.println("[ 1 ] Espada de Osso       (+10 Dano)   | Custo: 2 Osso");
            System.out.println("[ 2 ] Bomba de Pólvora     (+15 Dano)   | Custo: 1 Pólvora, 1 Madeira");
            System.out.println("[ 3 ] Machado de Ferro     (+15 Dano)   | Custo: 1 Madeira, 2 Ferro");
            System.out.println("[ 4 ] Armadura de Diamante (+9 Escudo)  | Custo: 3 Diamante");
            System.out.println("[ 5 ] Escudo de Ferro      (+5 Escudo)  | Custo: 2 Madeira, 1 Ferro");
            System.out.println("[ 6 ] Picareta de Ferro    (+12 Dano)   | Custo: 1 Madeira, 2 Ferro");
            System.out.println("[ 7 ] Espada de Diamante   (+20 Dano)   | Custo: 1 Madeira, 2 Diamante");
            System.out.println("[ 8 ] Poção Tóxica         (Veneno +5)  | Custo: 1 Pólvora, 1 Osso");
            System.out.println("[ 9 ] Relíquia Esmeralda   (Cura +20)   | Custo: 1 Esmeralda, 1 Lã");
            System.out.println("\n[ 0 ] Sair da Mesa de Crafting");

            int opcao = interfaceJogo.leitura(0, 9);
            if (opcao == 0) return;

            processarCraft(opcao, heroi, cartas);
            interfaceJogo.aguardarEnter("\n\u001B[33m>> Pressione Enter para continuar... \u001B[m");
        }
    }

    private void processarCraft(int opcao, Heroi heroi, DequeHeroi cartas) {
        boolean sucesso = false;
        String item = "";

        if (opcao == 1 && heroi.temOsso(2)) {
            heroi.gastarOsso(2);
            cartas.addCarta(new CartaDano("Espada de Osso", "+10 Dano", 2, 10));
            item = "Espada de Osso"; sucesso = true;
        } else if (opcao == 2 && heroi.temPolvora(1) && heroi.temMadeira(1)) {
            heroi.gastarPolvora(1); heroi.gastarMadeira(1);
            cartas.addCarta(new CartaDano("Bomba de Pólvora", "+15 Dano", 2, 15));
            item = "Bomba de Pólvora"; sucesso = true;
        } else if (opcao == 3 && heroi.temFerro(2) && heroi.temMadeira(1)) {
            heroi.gastarMadeira(1); heroi.gastarFerro(2);
            cartas.addCarta(new CartaDano("Machado de Ferro", "+15 Dano", 2, 15));
            item = "Machado de Ferro"; sucesso = true;
        } else if (opcao == 4 && heroi.temDiamante(3)) {
            heroi.gastarDiamante(3);
            cartas.addCarta(new CartaEscudo("Armadura Diamante", "+9 Escudo", 2, 9));
            item = "Armadura de Diamante"; sucesso = true;
        } else if (opcao == 5 && heroi.temFerro(1) && heroi.temMadeira(2)) {
            heroi.gastarFerro(1); heroi.gastarMadeira(2);
            cartas.addCarta(new CartaEscudo("Escudo de Ferro", "+5 Escudo", 1, 5));
            item = "Escudo de Ferro"; sucesso = true;
        } else if (opcao == 6 && heroi.temMadeira(1) && heroi.temFerro(2)) {
            heroi.gastarMadeira(1); heroi.gastarFerro(2);
            cartas.addCarta(new CartaDano("Picareta de Ferro", "+12 Dano", 2, 12));
            item = "Picareta de Ferro"; sucesso = true;
        } else if (opcao == 7 && heroi.temMadeira(1) && heroi.temDiamante(2)) {
            heroi.gastarMadeira(1); heroi.gastarDiamante(2);
            cartas.addCarta(new CartaDano("Espada Diamante", "+20 Dano", 3, 20));
            item = "Espada de Diamante"; sucesso = true;
        } else if (opcao == 8 && heroi.temPolvora(1) && heroi.temOsso(1)) {
            heroi.gastarPolvora(1); heroi.gastarOsso(1);
            cartas.addCarta(new CartaEfeito("Poção Tóxica", "5 Veneno", 2, 5, "Veneno"));
            item = "Poção Tóxica"; sucesso = true;
        } else if (opcao == 9 && heroi.temEsmeralda(1) && heroi.temLa(1)) {
            heroi.gastarEsmeralda(1); heroi.gastarLa(1);
            cartas.addCarta(new CartaEfeito("Relíquia Esmeralda", "+20 Vida", 2, 20, "Cura"));
            item = "Relíquia Esmeralda"; sucesso = true;
        }

        if (sucesso) {
            System.out.println("\n\u001B[32m>> Sucesso! O item '" + item + "' foi adicionado ao seu baralho!\u001B[m");
        } else {
            System.out.println("\n\u001B[31m>> Recursos insuficientes para criar este item!\u001B[m");
        }
    }
}