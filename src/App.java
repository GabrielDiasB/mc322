import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        Limpa();
        System.out.println("\u001B[1;36m=-=-=-=-= CRAFT & COMBATE =-=-=-=-=\u001B[m\n");
        System.out.printf("Digite o nome do seu personagem: ");
        Scanner entrada = new Scanner (System.in);
        String leitura = entrada.nextLine();
        Heroi heroi = new Heroi(leitura, 10, 3, 3);
        heroi.resetarEscudo();
        System.out.printf("\nSeja muito bem vindo, %s! Está um lindo dia ensolarado, vamos coletar recursos antes que anoiteça e sejamos atacados.\n", leitura);
        System.out.printf("\nUse o seu XP para explorar o mundo e obter matérias-primas. ");
        char res = 'n';
        Scanner resposta = new Scanner (System.in);
        while (res != 's') {
            System.out.print("Vamos começar nossa aventura? [s/n] ");
            res = resposta.next().charAt(0);
            if (res == 'n') {
                System.out.println("Que pena... Quando estiver pronto só falar.");
            }
        }
        Inimigo inimigoEscolhido = null;
        Limpa();
        heroi.titulo();
        heroi.atualiza();
        System.out.println("\n\u001B[33m>> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\u001B[m");
        while (true) {
            System.out.println("\n[ 1 ] Ganhar recursos \u001B[35m(-1XP)\u001B[m\n[ 2 ] Inventário e melhorias\u001B[m\n[ 3 ] Estou pronto\n");
            System.out.print("Digite a opção: ");
            int selecione = entrada.nextInt();
            while (selecione != 1 && selecione !=2 && selecione != 3) {
                System.out.println("\nOpção inválida. Tente novamente...\n");
                System.out.print("Digite a opção: ");
                selecione = entrada.nextInt();
            }
            if (selecione == 1) {
                if (heroi.getExp() >= 1) {
                    heroi.gastarExp(1);
                    Limpa();
                    heroi.titulo();
                    heroi.atualiza();

                    System.out.println("\n\u001B[33m>> " + heroi.getNome() + " explorou e obteve " + heroi.recursos() + "\u001B[m");
                } else {
                    Limpa();
                    heroi.titulo();
                    heroi.atualiza();
                    System.out.println("\n\u001B[31m>> Você não tem experiência suficiente para ganhar mais recursos.\u001B[m");
                }
            
            } else if (selecione == 2) {
                Limpa();
                heroi.titulo();
                heroi.atualiza();
                System.out.println(heroi.inventario());
                System.out.println("\n\u001B[33m>> Veja o seu inventário, " + heroi.getNome() + ". Quer melhorar algo: \u001B[m");
                while (true) {
                    System.out.println("\n[ 1 ] Soco >>>>> Espada de madeira \u001B[35m(-1XP e -2 madeiras)\u001B[m\n[ 2 ] Roupa normal >>>>> Armadura de Ferro \u001B[35m(-1XP e -2 ferros)\u001B[m\n[ 3 ] Voltar\n");
                    System.out.print("Digite a opção: ");
                    Scanner entra = new Scanner (System.in);
                    int n = entra.nextInt();
                    if (n == 1) {
                        break;
                    } else if (n == 2) {
                        break;
                    } else if (n == 3) {
                        Limpa();
                        heroi.titulo();
                        heroi.atualiza();
                        System.out.println("\n\u001B[33m>> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\u001B[m");
                        break;
                    } else {
                        System.out.println("Opção inválida, tente novamente...");
                    }
                    entra.close();
                }
                
            
            } else if (selecione == 3) {
                heroi.resetarExp();
                Limpa();
                heroi.titulo();
                heroi.atualiza();
                System.out.println("\n\u001B[33m>> " + heroi.getNome() + " encerrou a preparação e a noite chegou! Escolha quem irá enfrentar: \u001B[m");
                break;
            }   
        }
        while (inimigoEscolhido == null) {
            System.out.println("\n[ 1 ]\u001B[31m Zumbi\u001B[m\n[ 2 ]\u001B[31m Esqueleto\u001B[m\n[ 3 ]\u001B[31m Creeper\u001B[m\n");
            System.out.print("Digite a opção: ");
            int escolha = entrada.nextInt();
            if (escolha == 1) {
                inimigoEscolhido = new Inimigo("Zumbi", 20, 2, 3);

            } else if (escolha == 2) {
                inimigoEscolhido = new Inimigo("Esqueleto", 10, 1, 4);
        
            } else if (escolha == 3) {
                inimigoEscolhido = new Inimigo("Creeper", 25, 0, 10);
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
        System.out.println("\u001B[33m>> " + heroi.getNome() + " escolheu " + inimigoEscolhido.getNome() + "\u001B[m");
        while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
            if (heroi.getExp() > 0) {
                System.out.println("\n[ 1 ] Usar espada: +5 de dano \u001B[35m(-1XP)\u001B[m\n[ 2 ] Usar armadura: +1 de escudo \u001B[35m(-1XP)\u001B[m\n[ 3 ] Encerrar turno\n");
                System.out.print("Digite a opção: ");
                int opcao = entrada.nextInt();
                while (opcao != 1 && opcao !=2 && opcao != 3) {
                    System.out.println("\nOpção inválida. Tente novamente...\n");
                    System.out.print("Digite a opção: ");
                    opcao = entrada.nextInt();
                }
                CartaDano CartaDano = new CartaDano("carta de dano","Uma carta que causa dano", 1, 5);
                CartaEscudo CartaEscudo = new CartaEscudo("carta de escudo", "Uma carta que concede escudo", 1, 1);
                if (opcao == 1) {
                    if (heroi.getExp() >= CartaDano.getCusto()) {
                        int danoRealInimigo = CartaDano.usar(heroi, inimigoEscolhido);
                        heroi.gastarExp(CartaDano.getCusto());
                        Limpa();
                        heroi.titulo();
                        heroi.atualiza();
                        System.out.println("\nvs\n");
                        inimigoEscolhido.atualiza();
                        System.out.println("\u001B[33m>> " + heroi.getNome() + " usou a carta de dano! " + inimigoEscolhido.getNome() + " levou " + danoRealInimigo + " de dano. \u001B[m");
                    } else {
                        System.out.println("\u001B[31m>> Você não tem experiência suficiente para usar essa carta!\u001B[m");
                    }
                
                } else if (opcao == 2) {
                    if (heroi.getExp() >= CartaEscudo.getCusto()) {
                        if (heroi.getEscudo() >= heroi.getEscudoInicial()) {
                            System.out.println("\u001B[31m>> Escudo já está no máximo!\u001B[m");
                        } else {
                            heroi.gastarExp(CartaEscudo.getCusto());
                            CartaEscudo.usar(heroi, inimigoEscolhido);
                            Limpa();
                            heroi.titulo();
                            heroi.atualiza();
                            System.out.println("\nvs\n");
                            inimigoEscolhido.atualiza();
                            System.out.println("\u001B[33m>> " + heroi.getNome() + " usou a carta de escudo! \u001B[m");
                        }
                        
                    } else {
                        System.out.println("\u001B[31mVocê não tem experiência suficiente para usar essa carta!\u001B[m");
                    }
                } else if (opcao == 3) {
                    heroi.receberDano(inimigoEscolhido.atacar());
                    heroi.resetarExp();
                    heroi.resetarEscudo();
                    Limpa();
                    heroi.titulo();
                    heroi.atualiza();
                    System.out.println("\nvs\n");
                    inimigoEscolhido.atualiza();
                    System.out.println("\u001B[33m>> " + heroi.getNome() + " encerrou o turno!\u001B[m");
                    
                }
            } else {
                int danoReal = heroi.receberDano(inimigoEscolhido.atacar());
                heroi.resetarExp();
                heroi.resetarEscudo();
                Limpa();
                heroi.titulo();
                heroi.atualiza();
                System.out.println("\nvs\n");
                inimigoEscolhido.atualiza();
                System.out.println("\u001B[33m>> " + heroi.getNome() + " encerrou o turno! "  + inimigoEscolhido.getNome() + " atacou e causou " + danoReal + " de dano.\u001B[m");
            }
              
        }
        if (heroi.estaVivo()) {
            System.out.println("\n\u001B[1;32mPARABÉNS!!! Você derrotou o inimigo!\u001B[m\n");
        }
        if (inimigoEscolhido.estaVivo()) {
            System.out.println("\n\u001B[1;31mQUE PENA, você foi derrotado!\u001B[m");
            System.out.println("\u001B[1;31mNão foi dessa vez... Tente novamente!\u001B[m\n");
            entrada.close();
            resposta.close();
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
        }
    }
}