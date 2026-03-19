import java.util.Scanner; 

public class App {
    private static Scanner scanner = new Scanner (System.in);
    public static void main(String[] args) throws Exception {
        Limpa();
        titulo();
        destaque("Digite o nome do seu personagem: ");
        String nomeHeroi = scanner.nextLine();
        Heroi heroi = new Heroi(nomeHeroi, 100, 10, 3);
        heroi.resetarEscudo();
        DequeHeroi cartas = new DequeHeroi();
        cartas.addCarta(new CartaDano("Chute Voadora", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaDano("Golpe de Punho", "+2 Dano", 1, 2));
        cartas.addCarta(new CartaDano("Soco Mortal", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaDano("Flechada Precisa", "+7 Dano", 2, 7));
        cartas.addCarta(new CartaDano("Espadada Aguda", "+5 Dano", 2, 5));
        cartas.addCarta(new CartaDano("Espadada Afiada", "+6 Dano", 2, 6));
        cartas.addCarta(new CartaEscudo("Desvio Ágil", "+1 Escudo", 1, 1));
        cartas.addCarta(new CartaEscudo("Reflexo Aguçado", "+2 Escudo", 1, 2));
        cartas.addCarta(new CartaEscudo("Defesa Armadura", "+5 Escudo", 2, 5));
        cartas.addCarta(new CartaEscudo("Escudo Potente", "+3 Escudo", 1, 3));
        
        System.out.printf("\nSeja muito bem vindo, %s!\n", nomeHeroi);
        System.out.printf("\nEstá um lindo dia ensolarado, vamos coletar recursos antes que anoiteça e sejamos atacados.\n", nomeHeroi);
        System.out.println("\nUse o seu XP para explorar o mundo e obter matérias-primas.\n");
        char res = 'n';
        while (res != 's') {
            destaque("Vamos começar nossa aventura? [s/n] ");
            res = scanner.next().charAt(0);
            if (res == 'n') {
                System.out.println("\nQue pena... Quando estiver pronto só falar.\n");
            }
        }

        for (int num_batalha = 0; num_batalha < 4; num_batalha++) {
            if (!heroi.estaVivo()) {
                break;
            }
            heroi.expProgresso(num_batalha);
            heroi.resetarExp();
            atualizaTela(heroi);
            if (num_batalha > 0) {
                destaque("\n>> Você venceu a " + num_batalha + "ª batalha e ganhou +" + num_batalha + "XP! Já amanheceu, continue progredindo!\n");
            } else {
                destaque("\n>> Está de dia! Aproveite para se preparar " + heroi.getNome() + "!\n");
            }

            while (true) {
                System.out.println("\n\u001B[36m[ 1 ]\u001B[m Explorar o mapa (-1XP)\n\u001B[36m[ 2 ]\u001B[m Ataques e defesas\u001B[m\n\u001B[36m[ 3 ]\u001B[m Estou pronto para a noite!\n");
                System.out.print("Digite a opção: ");
                int selecione = scanner.nextInt();
                while (selecione != 1 && selecione !=2 && selecione != 3) {
                    System.out.println("\nOpção inválida. Tente novamente...\n");
                    System.out.print("Digite a opção: ");
                    selecione = scanner.nextInt();
                }
                if (selecione == 1) {
                    if (heroi.getExp() >= 1) {
                        heroi.gastarExp(1);
                        atualizaTela(heroi);

                        destaque("\n\u001B[33m>> " + heroi.getNome() + " explorou e obteve " + heroi.recursos() + "\n");
                    } else {
                        atualizaTela(heroi);
                        System.out.println("\n\u001B[31m>> Você não tem experiência suficiente para ganhar mais recursos.\u001B[m");
                    }
                
                } else if (selecione == 2) {
                    atualizaTela(heroi);
                    System.out.println("\n=========== Cartas Desbloqueadas ===========\n");
                    cartas.mostraCartas();
                    System.out.println("\n===========================================");
                    
                
                } else if (selecione == 3) {
                    break;
                }   
            }

            DequeInimigo filaBatalhas = new DequeInimigo();
            cartas.inicializacao();
            cartas.comprarAtual();

            if (num_batalha == 3) {
                filaBatalhas.adicionaNoFim(criarBossFinal());
            } else {
                filaBatalhas.adicionaNoFim((gerarInimigoDaFase(num_batalha)));
            }

            while (heroi.estaVivo() && !filaBatalhas.vazio()) {
                Inimigo inimigoEscolhido = filaBatalhas.removerDoInicio();

                heroi.resetarExp();
                heroi.resetarEscudo();
                atualizaTela(heroi);
                System.out.println("\nvs\n");
                inimigoEscolhido.atualiza();

                if (num_batalha == 3) {
                    destaque(">> Batalha final contra " + inimigoEscolhido.getNome() + "!\n");
                } else {
                    destaque(">> Preparação encerrada e a " + (num_batalha + 1) + "ª de 4 noites chegou. " + heroi.getNome() + " irá enfrentar " + inimigoEscolhido.getNome() + "!\n");
                }

                while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
                    if (heroi.getExp() > 0) {
                        System.out.println("\n=========== Escolha suas cartas ===========\n");
                        cartas.mostraAtual();
                        System.out.println("\n[ 0 ] " + "Encerrar turno\n");
                        System.out.println("===========================================\n");
                        System.out.print("Digite a opção: ");
                        int opcao = scanner.nextInt();
                        
                        if (opcao == 0) {
                            heroi.zerarExp();
                        } else {
                            if (cartas.getAtual().get(opcao - 1).getCusto() > heroi.getExp()) {
                                atualizaTela(heroi);
                                System.out.println("\nvs\n");
                                inimigoEscolhido.atualiza();
                                System.out.println("\u001B[31m>> Sem XP suficiente para essa carta!\u001B[m");   
                            } else {
                                int dado = cartas.getAtual().get(opcao - 1).usar(heroi, inimigoEscolhido);
                                String texto = cartas.getAtual().get(opcao - 1).usarTexto(heroi, inimigoEscolhido, dado);
                                heroi.gastarExp(cartas.getAtual().get(opcao - 1).getCusto());
                                cartas.usar(opcao - 1);
                                atualizaTela(heroi);
                                System.out.println("\nvs\n");
                                inimigoEscolhido.atualiza();
                                destaque(">> " + texto);  
                            }
                             
                        }
                    } else {
                        int danoReal = heroi.receberDano(inimigoEscolhido.atacar());
                        heroi.resetarExp();
                        heroi.resetarEscudo();
                        cartas.descartarAtual();
                        cartas.comprarAtual();
                        atualizaTela(heroi);
                        System.out.println("\nvs\n");
                        inimigoEscolhido.atualiza();
                        if (danoReal > 0) {
                            destaque(">> " + heroi.getNome() + " encerrou o turno! " + inimigoEscolhido.getNome() + " atacou e causou " + danoReal + " de dano.\n");
                        } else {
                            destaque(">> " + heroi.getNome() + " encerrou o turno! " + heroi.getNome() + " bloqueou o ataque com o escudo!\n");
                        }
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
                return new Inimigo("Esqueleto Whiter", 15, 4, 10);    
            }
        }
        if (fase == 2){
            if (sorteio ==0){
                return new Inimigo("Whiter", 30, 4, 12);
            } else if (sorteio == 1){
                return new Inimigo ("Warden", 40, 3, 15);
            } else {
                return new Inimigo("Guaridão", 30, 4, 10);
            }
        }
        return null;
    }

    public static Inimigo criarBossFinal(){
        return new Inimigo("Ender Dragon", 50, 5, 20);
    }


    public static void titulo() {
        System.out.println("\u001B[1;36m╔══════════════════════════════════╗\u001B[m");
        System.out.println("\u001B[1;36m║         CRAFT & COMBATE          ║\u001B[m");
        System.out.println("\u001B[1;36m╚══════════════════════════════════╝\u001B[m\n");
    }

    public static void atualizaTela(Heroi heroi) {
        Limpa();
        titulo();
        heroi.atualiza();
    }

    public static void destaque(String texto) {
        System.out.print("\u001B[33m" + texto + "\u001B[m");
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