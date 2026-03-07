import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        System.out.printf("Digite o nome do seu personagem: ");
        Scanner entrada = new Scanner (System.in);
        String leitura = entrada.nextLine();
        System.out.printf("Seja muito bem vindo, %s! Escolha o seu inimigo:\n", leitura);
        Heroi heroi = new Heroi(leitura, 20, 0, 20, 3);
        Inimigo inimigoEscolhido = null;
        


        
        while (inimigoEscolhido == null) {
            System.out.println("[ 1 ] Zumbi\n[ 2 ] Esqueleto\n[ 3 ] Creeper");
            System.out.print("Digite a opção: ");
            int escolha = entrada.nextInt();
            if (escolha == 1) {
                System.out.println("Você escolheu o zumbi!");
                inimigoEscolhido = new Inimigo("zumbi", 20, 2, 2);

            } else if (escolha == 2) {
                System.out.println("Você escolheu o esqueleto!");
                inimigoEscolhido = new Inimigo("esqueleto", 20, 0, 5);
        
            } else if (escolha == 3) {
                System.out.println("Você escolheu o creeper!");
                inimigoEscolhido = new Inimigo("creeper", 20, 0, 10);
            } else {
                System.out.println("Opção inválida. Tente novamente...");
                System.out.println("Escolha novamente:");
            }
        }
        

        while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
            while (heroi.getExp() > 0) {
                System.out.println(heroi.getNome() + " (" + heroi.getVida() + " / " + heroi.getVidaInicial() + ")" + " vs " + inimigoEscolhido.getNome() + " (" + inimigoEscolhido.getVida() + " / " + inimigoEscolhido.getVidaInicial() + ")");
                System.out.println("(" + heroi.getExp() + " / " + heroi.getExpInicial() + ") de experiência disponível");
                System.out.println("\u001B[36m" + "\n[ 1 ] Usar carta de dano\n[ 2 ] Usar carta de escudo\n[ 3 ] Encerrar turno\n" + "\u001B[0m");
                System.out.println("Digite a opção: ");
                int opcao = entrada.nextInt();
                while (opcao != 1 && opcao !=2 && opcao != 3) {
                    System.out.println("Opção inválida. Tente novamente...");
                    System.out.println("Digite a opção: ");
                    opcao = entrada.nextInt();
                }
                CartaDano CartaDano = new CartaDano("carta de dano", 1, 5);
                if (opcao == 1) {
                    if (heroi.getExp() >= CartaDano.getCustoExp()) {
                        System.out.println(heroi.getNome() + " usou a carta de dano!");
                        inimigoEscolhido.receberDano(CartaDano.usar());
                        heroi.gastarExp(CartaDano.getCustoExp());
                    
                    } else {
                        System.out.println("Você não tem experiência suficiente para usar essa carta!");
                    }
                
                } else if (opcao == 2) {
                    if (heroi.getExp() >= 1) {
                        System.out.println(heroi.getNome() + " usou a carta de escudo!");
                        heroi.gastarExp(1);
                    } else {
                        System.out.println("Você não tem experiência suficiente para usar essa carta!");
                    }
                } else if (opcao == 3) {
                    System.out.println(heroi.getNome() + " encerrou o turno!");
                }
            }   
        }

    }
}