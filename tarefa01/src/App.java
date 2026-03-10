import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        Limpa();
        System.out.println("\u001B[1;36m=-=-=-=-= CRAFT & COMBATE =-=-=-=-=\u001B[m\n");
        System.out.printf("Digite o nome do seu personagem: ");
        Scanner entrada = new Scanner (System.in);
        String leitura = entrada.nextLine();
        System.out.printf("\nSeja muito bem vindo, %s! Escolha o seu inimigo:\n", leitura);
        Heroi heroi = new Heroi(leitura, 20, 0, 20, 3, 5);
        Inimigo inimigoEscolhido = null;
        


        
        while (inimigoEscolhido == null) {
            System.out.println("\u001B[31m\n[ 1 ] Zumbi\n[ 2 ] Esqueleto\n[ 3 ] Creeper\n\u001B[m");
            System.out.print("Digite a opção: ");
            int escolha = entrada.nextInt();
            if (escolha == 1) {
                inimigoEscolhido = new Inimigo("Zumbi", 20, 2, 2, 2);

            } else if (escolha == 2) {
                inimigoEscolhido = new Inimigo("Esqueleto", 30, 0, 5, 2);
        
            } else if (escolha == 3) {
                inimigoEscolhido = new Inimigo("Creeper", 25, 0, 10, 2);
            } else {
                System.out.println("Opção inválida. Tente novamente...");
                System.out.println("Escolha novamente:");
            }
        }
        
        Limpa();
        heroi.titulo();
        heroi.atualiza();
        System.out.println("\nvs\n");
        inimigoEscolhido.atualiza();
        System.out.println(heroi.getNome() + " escolheu " + inimigoEscolhido.getNome());
        while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
            if (heroi.getExp() > 0) {
                System.out.println("\u001B[36m" + "\n[ 1 ] Usar carta de dano (Custo: 1 XP)\n[ 2 ] Usar carta de escudo (Custo: 1 XP)\n[ 3 ] Encerrar turno (Custo: 0 XP)\n" + "\u001B[0m");
                System.out.print("Digite a opção: ");
                int opcao = entrada.nextInt();
                while (opcao != 1 && opcao !=2 && opcao != 3) {
                    System.out.println("Opção inválida. Tente novamente...");
                    System.out.print("Digite a opção: ");
                    opcao = entrada.nextInt();
                }
                CartaDano CartaDano = new CartaDano("carta de dano", 1, 5);
                CartaEscudo CartaEscudo = new CartaEscudo("carta de escudo", 1, 1);
                if (opcao == 1) {
                    if (heroi.getExp() >= CartaDano.getCustoExp()) {
                        inimigoEscolhido.receberDano(CartaDano.usar());
                        heroi.gastarExp(CartaDano.getCustoExp());
                        Limpa();
                        heroi.titulo();
                        heroi.atualiza();
                        System.out.println("\nvs\n");
                        inimigoEscolhido.atualiza();
                        System.out.println(heroi.getNome() + " usou a carta de dano!");
                    } else {
                        System.out.println("Você não tem experiência suficiente para usar essa carta!");
                    }
                
                } else if (opcao == 2) {
                    if (heroi.getExp() >= 1) {
                        if (heroi.getEscudo() >= heroi.getEscudoInicial()) {
                            System.out.println("Escudo já está no máximo!");
                        } else {
                            heroi.gastarExp(1);
                            heroi.receberEscudo(CartaEscudo.usar());
                            Limpa();
                            heroi.titulo();
                            heroi.atualiza();
                            System.out.println("\nvs\n");
                            inimigoEscolhido.atualiza();
                            System.out.println(heroi.getNome() + " usou a carta de escudo!");
                        }
                        
                    } else {
                        System.out.println("Você não tem experiência suficiente para usar essa carta!");
                    }
                } else if (opcao == 3) {
                    heroi.resetarExp();
                    heroi.resetarEscudo();
                    Limpa();
                    heroi.titulo();
                    heroi.atualiza();
                    System.out.println("\nvs\n");
                    inimigoEscolhido.atualiza();
                    System.out.println(heroi.getNome() + " encerrou o turno!");
                    
                }
            } else {
                System.out.println(heroi.getNome() + " encerrou o turno!");
                heroi.resetarExp();
                heroi.resetarEscudo();
            }
              
        }
        if (heroi.estaVivo()) {
            System.out.println("\n\u001B[1;32mPARABÉNS!!! Você derrotou o inimigo!\u001B[m\n");
        }
        if (inimigoEscolhido.estaVivo()) {
            System.out.println("\n\u001B[1;31mNão foi dessa vez... Tente novamente!\u001B[m\n");
        }

    }
    public static void Limpa() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Trata possíveis erros
        }
    }
}