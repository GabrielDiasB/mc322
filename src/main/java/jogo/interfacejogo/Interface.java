package jogo.interfacejogo;
import jogo.entidades.Heroi;


import java.util.Scanner;

/**
 * Camada de interface textual do jogo (terminal), centralizando entrada e saida.
 */
public class Interface {
    private final Scanner leitor;

    /**
     * Cria a interface com um scanner compartilhado.
     *
     * @param leitor scanner usado para ler entradas do jogador
     */
    public Interface(Scanner leitor) {
        this.leitor = leitor;
    }

    /**
     * Limpa a tela e exibe o estado atual do heroi.
     *
     * @param heroi heroi exibido na interface
     */
    public void atualizaTela(Heroi heroi) {
        limpar();
        titulo();
        heroi.atualiza();
        System.out.println();
        System.out.println(desenhoHeroi());
    }

    /** Exibe o titulo principal do jogo. */
    public void titulo() {
        System.out.println("\u001B[1;36m╔══════════════════════════════════╗\u001B[m");
        System.out.println("\u001B[1;36m║        CRAFT & COMBATE           ║\u001B[m");
        System.out.println("\u001B[1;36m╚══════════════════════════════════╝\u001B[m\n");
    }

    /**
     * Destaca uma mensagem informativa em amarelo.
     *
     * @param texto mensagem a ser exibida
     */
    public void destaque(String texto) {
        System.out.print("\u001B[33m" + texto + "\u001B[m");
    }

    /**
     * Destaca uma mensagem de erro em vermelho.
     *
     * @param texto mensagem a ser exibida
     */
    public void destaqueErro(String texto) {
        System.out.print("\u001B[31m" + texto + "\u001B[m");
    }

    /** Limpa o terminal usando a estrategia adequada para o sistema operacional. */
    public void limpar() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Evita interromper o jogo caso a limpeza falhe.
        }
    }

    /**
     * Le uma opcao inteira dentro de um intervalo valido.
     *
     * @param min menor valor permitido
     * @param max maior valor permitido
     * @return opcao lida
     */
    public int leitura(int min, int max) {
        while (true) {
            System.out.print("Digite a opcao: ");
            if (leitor.hasNextInt()) {
                int opcao = leitor.nextInt();
                if (opcao >= min && opcao <= max) {
                    return opcao;
                }
                System.out.println("\u001B[31mOpcao invalida. Tente novamente...\u001B[m\n");
            } else {
                System.out.println("\u001B[31mInteiro invalido. Tente novamente...\u001B[m\n");
                leitor.next();
            }
        }
    }

    /**
     * Le uma linha apos exibir um prompt.
     *
     * @param mensagem texto exibido antes da leitura
     * @return linha informada pelo jogador
     */
    public String lerLinha(String mensagem) {
        System.out.print(mensagem);
        return leitor.nextLine();
    }

    /**
     * Le um caractere simples para confirmacoes.
     *
     * @param mensagem texto exibido antes da leitura
     * @return primeiro caractere digitado (minusculo)
     */
    public char lerConfirmacao(String mensagem) {
        System.out.print(mensagem);
        return Character.toLowerCase(leitor.next().charAt(0));
    }

    /**
     * Aguarda o jogador pressionar Enter para continuar.
     *
     * @param mensagem texto exibido antes da espera
     */
    public void aguardarEnter(String mensagem) {
        if (leitor.hasNextLine()) {
            leitor.nextLine();
        }
        System.out.print(mensagem);
        leitor.nextLine();
    }

    /**
     * Retorna a representacao ASCII do heroi.
     *
     * @return arte ASCII do heroi
     */
    public String desenhoHeroi() {
        return  "   O  \n" +
                "  /|\\ \n" +
                "  / \\ \n";
    }

    /**
     * Retorna a arte ASCII de um inimigo pelo nome.
     *
     * @param nomeInimigo nome do inimigo
     * @return arte ASCII correspondente
     */
    public String obterArteInimigo(String nomeInimigo) {
        switch (nomeInimigo) {
            case "Zumbi":
                return  "  [o] \n" +
                        "  /|] \n" +
                        "  / \\ \n";
            case "Esqueleto":
                return  "   ☠  \n" +
                        "  /|# \n" +
                        "  / \\ \n";
            case "Creeper":
                return  "  [ ] \n" +
                        "  / \\ \n";
            case "Aranha das Cavernas":
                return  " /\\( )/\\ \n" +
                        "   ^ ^   \n";
            case "Blaze":
                return  " \\ | / \n" +
                        " - o - \n" +
                        " / | \\ \n";
            case "Enderman":
                return  "   O  \n" +
                        "  /|\\ \n" +
                        "   |  \n" +
                        "  / \\ \n";
            case "Esqueleto Wither":
                return  "   ☠  \n" +
                        "  [|] \n" +
                        "  / \\ \n";
            case "Bruxa":
                return  "   ^  \n" +
                        "  / \\ \n" +
                        "  /|\\ \n";
            case "Wither":
                return  " ☠ ☠ ☠ \n" +
                        "  \\|/  \n" +
                        "   |   \n";
            case "Warden":
                return  " {o.o} \n" +
                        " /[_]\\ \n" +
                        "  / \\  \n";
            case "Guardião":
                return  " <[O]> \n" +
                        "  \\|/  \n";
            case "Evoker":
                return  "   ~   \n" +
                        "  (o)  \n" +
                        "  /|\\  \n";
            case "Ender Dragon":
                return  "  /|\\   /|\\  \n" +
                        " < O >=< O > \n" +
                        "  \\|/   \\|/  \n";
            default:
                return  "  (?) \n" +
                        "  /|\\ \n" +
                        "  / \\ \n";
        }
    }
}
