package jogo.app;
import jogo.cartas.DequeHeroi;
import jogo.entidades.Heroi;
import jogo.interfacejogo.Interface;
import jogo.servicos.Craft;
import jogo.servicos.Fluxocombate;


import java.util.Scanner; 

/**
 * Ponto de entrada da aplicação e orquestrador do loop principal do jogo.
 */
public class App {
    /** Scanner compartilhado para ler toda a entrada do usuário durante o jogo. */
    private static final Scanner leitorEntrada = new Scanner(System.in);

    /** Classe utilitária sem instância. */
    private App() {
    }

    /**
     * Inicia a jornada, controla a preparação do herói e executa as batalhas.
     *
     * @param args argumentos da linha de comando, não utilizados
     * @throws Exception propagado quando ocorre falha inesperada na execução do jogo
     */
    public static void main(String[] args) throws Exception {
        Interface interfaceJogo = new Interface(leitorEntrada);
        Craft servicoCraft = new Craft(interfaceJogo);
        Fluxocombate fluxoCombate = new Fluxocombate(interfaceJogo, servicoCraft);

        interfaceJogo.limpar();
        interfaceJogo.titulo();
        interfaceJogo.destaque("Digite o nome do seu personagem: ");
        String nomeHeroi = leitorEntrada.nextLine();

        Heroi heroi = new Heroi(nomeHeroi, 100, 10, 3);
        heroi.resetarEscudo();

        DequeHeroi cartas = new DequeHeroi();
        servicoCraft.inicializarBaralhoBase(cartas);
        
        System.out.printf("\nSeja muito bem vindo(a), %s!\n", nomeHeroi);
        System.out.printf("\nEstá um lindo dia ensolarado, vamos coletar recursos antes que anoiteça e sejamos atacados.\n");
        System.out.println("\nUse o seu XP para explorar o mundo e obter matérias-primas.\n");

        char res = 'n';
        while (res != 's') {
            res = interfaceJogo.lerConfirmacao("\u001B[33mVamos começar nossa aventura? [s/n] \u001B[m");
            if (res == 'n') {
                System.out.println("\nQue pena... Quando estiver pronto só falar.\n");
            }
        }

        fluxoCombate.executarCampanha(heroi, cartas);
    }
}