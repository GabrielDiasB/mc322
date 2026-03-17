import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        Limpa();
        System.out.println("\u001B[1;36m=-=-=-=-= CRAFT & COMBATE =-=-=-=-=\u001B[m\n");
        System.out.printf("Digite o nome do seu personagem: ");
        Scanner entrada = new Scanner (System.in);
        String leitura = entrada.nextLine();
        Heroi heroi = new Heroi(leitura, 100, 3, 3);
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

        for (int num_batalha = 0; num_batalha < 4; num_batalha++) {
            heroi.resetarExp();
            Limpa();
            heroi.titulo();
            heroi.atualiza();
            if (num_batalha > 0) {
                System.out.println("\n\u001B[33m>> Você venceu a " + num_batalha + "ª batalha! Já amanheceu, continue progredindo!\u001B[m");
            } else {
                System.out.println("\n\u001B[33m>> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\u001B[m");
            }

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
                        System.out.println("\nCartas atuais:\n\n. Soco (5 de dano)\n\n[ 1 ] Craftar espada de madeira (7 de dano) \u001B[35m(-1XP e -2 madeiras)\u001B[m\n[ 2 ] Craftar armadura de ferro (+1 escudo fixo) \u001B[35m(-1XP e -2 ferros)\u001B[m\n[ 3 ] Voltar\n");
                        System.out.print("Digite a opção: ");
                        Scanner entra = new Scanner (System.in);
                        int n = entra.nextInt();
                        if (n == 1) {
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
                    break;
                }   
            }
            DequeInimigo filaBatalhas = new DequeInimigo();

            
            if (num_batalha == 3) {
                filaBatalhas.adicionaNoFim(criarBossFinal());
            } else {
                filaBatalhas.adicionaNoFim((gerarInimigoDaFase(num_batalha)));
            }

            while (heroi.estaVivo() && !filaBatalhas.vazio()) {
                Inimigo inimigoEscolhido = filaBatalhas.removerDoInicio();

                heroi.resetarExp();
                heroi.resetarEscudo();

                Limpa();
                heroi.titulo();
                heroi.atualiza();
                System.out.println("\nvs\n");
                inimigoEscolhido.atualiza();

                if (num_batalha == 3) {
                    System.out.println("\u001B[31m>> BATALHA FINAL contra " + inimigoEscolhido.getNome() + "!\u001B[m");
                } else {
                    System.out.println("\u001B[33m>> " + heroi.getNome() + " encerrou a preparação e a " + (num_batalha + 1) + "ª de 4 noites chegou. Você irá enfrentar " + inimigoEscolhido.getNome() + "!\u001B[m");
                }

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
                                heroi.gastarExp(CartaEscudo.getCusto());
                                CartaEscudo.usar(heroi, inimigoEscolhido);
                                Limpa();
                                heroi.titulo();
                                heroi.atualiza();
                                System.out.println("\nvs\n");
                                inimigoEscolhido.atualiza();
                                System.out.println("\u001B[33m>> " + heroi.getNome() + " usou a carta de escudo! \u001B[m");

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
            }

            if (heroi.estaVivo()) {
                System.out.println("\n\u001B[1;32mPARABÉNS!!! Você venceu as 4 batalhas e derrotou o boss final!\u001B[m\n");
            } else {
                System.out.println("\n\u001B[1;31mQUE PENA, você foi derrotado!\u001B[m");
                System.out.println("\u001B[1;31mNão foi dessa vez... Tente novamente!\u001B[m\n");
            }

        }
        entrada.close();
        resposta.close();
    }
        

    public static Inimigo gerarInimigoDaFase(int fase){
        int sorteio =  (int) (Math.random() * 3);
        if (fase == 0) {
            if (sorteio == 0) {
                return new Inimigo("Zumbi", 20, 2, 3);
            } else if (sorteio == 1) {
                return new Inimigo("Esqueleto", 10, 1, 4);
            } else {
                return new Inimigo("Creeper", 20, 0, 5);
            }
        }
        if (fase == 1){
            if (sorteio == 0){
                return new Inimigo("Blaze", 25, 3, 8);
            } else if (sorteio == 1){
                return new Inimigo("Enderman", 30, 1, 10);
            } else {
                return new Inimigo("Esqueleto Whiter", 15, 8, 10);    
            }
        }
        if (fase == 2){
            if (sorteio ==0){
                return new Inimigo("Whiter", 30, 8, 12);
            } else if (sorteio == 1){
                return new Inimigo ("Warden", 40, 5, 15);
            } else {
                return new Inimigo("Guaridão", 30, 5, 10);
            }
        }
        return null;
    }

    public static Inimigo criarBossFinal(){
        return new Inimigo("Ender Dragon", 50, 10, 20);
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