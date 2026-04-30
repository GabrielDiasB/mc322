package jogo.eventos;
import jogo.cartas.*;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;

/**
 * Evento narrativo de Escolha onde o jogador encontra um Villager Ambulante.
 */
public class ComercianteVillager extends Evento {

    @Override
    public boolean iniciar(Heroi heroi, DequeHeroi cartas, Interface interfaceJogo) {
        interfaceJogo.limpar();
        System.out.println("\u001B[35m============================================================\u001B[m");
        System.out.println("\u001B[1;35m|                  COMERCIANTE AMBULANTE                   |\u001B[m");
        System.out.println("\u001B[35m============================================================\u001B[m\n");
        
        System.out.println("Hmm! Hrmm! Um Villager Ambulante cruza o seu caminho.");
        System.out.println("Ele abre o seu casaco e revela itens raros.\n");
        
        System.out.println("\u001B[33m>> Seu Inventário: " + heroi.getExp() + " de XP | Esmeraldas: " + (heroi.temEsmeralda(1) ? "Sim" : "Não") + "\u001B[m\n");
        System.out.println("[ 1 ] Trocar 1 Esmeralda por uma Carta Rara (Poção de Cura Avançada)");
        System.out.println("[ 2 ] Pagar 3 de XP para aprimorar as suas técnicas (Remove uma carta básica)");
        System.out.println("[ 3 ] Ignorar e seguir viagem");
        System.out.println("\n------------------------------------------------------------\n");

        int opcao = interfaceJogo.leitura(1, 3);

        if (opcao == 1) {
            if (heroi.temEsmeralda(1)) {
                heroi.gastarEsmeralda(1);
                cartas.addCarta(new CartaEfeito("Cura Avançada", "+30 Vida", 2, 30, "Cura"));
                System.out.println("\n\u001B[32m>> Hrmm! Troca justa. Você recebeu a 'Cura Avançada'!\u001B[m");
            } else {
                System.out.println("\n\u001B[31m>> O Villager balança a cabeça negativamente. Você não tem Esmeraldas.\u001B[m");
            }
        } else if (opcao == 2) {
            if (heroi.getExp() >= 3) {
                heroi.gastarExp(3);
                System.out.println("\n\u001B[32m>> O Villager pegou 3 de XP seus e ajudou-o a organizar as suas ideias.\u001B[m");
            } else {
                System.out.println("\n\u001B[31m>> Energia (XP) insuficiente para realizar esta troca!\u001B[m");
            }
        } else {
            System.out.println("\n\u001B[33m>> Você acena para o Villager e continua a sua jornada com segurança.\u001B[m");
        }

        interfaceJogo.aguardarEnter("\n\u001B[33m>> Pressione Enter para continuar... \u001B[m");
        return heroi.estaVivo();
    }
}