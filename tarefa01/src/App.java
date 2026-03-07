import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        System.out.printf("Digite o nome do seu personagem: ");
        Scanner entrada = new Scanner (System.in);
        String leitura = entrada.nextLine();
        System.out.printf("Seja muito bem vindo, %s! Escolha o que deseja fazer:\n", leitura);
        Heroi heroi = new Heroi(leitura, 20, 5, 20);
        while (heroi.estaVivo()) {
            System.out.println("\u001B[36m" + "\n[ 1 ] Receber dano\n[ 2 ] Ganhar escudo\n[ 3 ] Está vivo?\n[ 4 ] Sair do jogo\n" + "\u001B[0m");
            System.out.print("Digite a opção: ");
            int opcao = entrada.nextInt();
            System.out.println();
            if (opcao == 1) {
                System.out.println(heroi.receberDano());
            } else if (opcao == 2) {
                System.out.println(heroi.ganharEscudo());
            } else if (opcao == 3) {
                System.out.println(heroi.estaVivo());
            } else if (opcao == 4) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente...");
            }
        }
    }
}