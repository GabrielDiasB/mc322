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
        cartas.addCarta(new CartaEscudo("Desvio Ágil", "+1 Escudo", 1, 1));
        cartas.addCarta(new CartaEscudo("Reflexo Aguçado", "+2 Escudo", 1, 2));
        
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
                int selecione = leitura(1, 3);
                if (selecione == 1) {
                    if (heroi.getExp() >= 1) {
                        heroi.gastarExp(1);
                        atualizaTela(heroi);
                        destaque("\n\u001B[33m>> " + heroi.getNome() + " explorou e obteve " + heroi.recursos() + "\n");
                    } else {
                        atualizaTela(heroi);
                        System.out.println("\n\u001B[31m>> Você não tem experiência suficiente para ganhar mais recursos.\u001B[m");
                    }
                    destaque(heroi.inventario());
                
                } else if (selecione == 2) {
                    atualizaTela(heroi);
                    System.out.println("\n=========== Cartas Desbloqueadas ===========\n");
                    cartas.mostraCartas();
                    System.out.println("\n=========== Craftar Novas Cartas ===========\n");
                    System.out.println("[ 1 ] " + String.format("%-17s", "Flechada Precisa") + "==> " + String.format("%-12s", "+7 Dano") + "-" + 2 + "XP");
                    System.out.println("[ 2 ] " + String.format("%-17s", "Espadada Aguda") + "==> " + String.format("%-12s", "+5 Dano") + "-" + 2 + "XP");
                    System.out.println("[ 3 ] " + String.format("%-17s", "Espadada Afiada") + "==> " + String.format("%-12s", "+6 Dano") + "-" + 2 + "XP");
                    System.out.println("[ 4 ] " + String.format("%-17s", "Defesa Armadura") + "==> " + String.format("%-12s", "+5 Escudo") + "-" + 2 + "XP");
                    System.out.println("[ 5 ] " + String.format("%-17s", "Escudo Potente") + "==> " + String.format("%-12s", "+3 Escudo") + "-" + 1 + "XP");
                    System.out.println("\n[ 0 ] Voltar para o menu");
                    System.out.println("\n===========================================");
                    int nova_carta = leitura(0, 5);
                    if (nova_carta == 1) {
                        cartas.addCarta(new CartaDano("Flechada Precisa", "+7 Dano", 2, 7));
                        atualizaTela(heroi);
                        destaque("\nCarta Flechada Precisa adicionada com sucesso ao seu deque de combate!\n");
        
                    } else if (nova_carta == 2) {
                        cartas.addCarta(new CartaDano("Espadada Aguda", "+5 Dano", 2, 5));
                        atualizaTela(heroi);
                        destaque("\nCarta Espadada Aguda adicionada com sucesso ao seu deque de combate!\n");
        
                    } else if (nova_carta == 3) {
                        cartas.addCarta(new CartaDano("Espadada Afiada", "+6 Dano", 2, 6));
                        atualizaTela(heroi);
                        destaque("\nCarta Espadada Afiada adicionada com sucesso ao seu deque de combate!\n");
                    } else if (nova_carta == 4) {
                        cartas.addCarta(new CartaEscudo("Defesa Armadura", "+5 Escudo", 2, 5));
                        atualizaTela(heroi);
                        destaque("\nCarta Defesa Armadura adicionada com sucesso ao seu deque de combate!\n");
                    } else if (nova_carta == 5) {
                        cartas.addCarta(new CartaEscudo("Escudo Potente", "+3 Escudo", 1, 3));
                        atualizaTela(heroi);
                        destaque("\nCarta Escudo Potente adicionada com sucesso ao seu deque de combate!\n");
                    } else {
                        atualizaTela(heroi);
                        continue;
                    }
                    
                
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
                    destaque(">> Noite 4/4. Batalha final contra " + inimigoEscolhido.getNome() + "!");
                } else {
                    destaque(">> Noite " + (num_batalha + 1) + "/4:");
                }

                while (heroi.estaVivo() && inimigoEscolhido.estaVivo()) {
                    if (heroi.getExp() == heroi.getExpInicial()) {
                        destaque(inimigoEscolhido.anunciarProximaAcao());
                    }

                    if (heroi.getExp() > 0) {
                        System.out.println("\n\n=========== Escolha suas cartas ===========\n");
                        cartas.mostraAtual();
                        System.out.println("\n[ 0 ] " + "Encerrar turno\n");
                        System.out.println("===========================================\n");
                        int opcao = leitura(0, 5);
                        if (opcao == 0) {
                            heroi.zerarExp();
                        } else {
                            while (cartas.getAtual().get(opcao - 1) == null) {
                                System.out.println("Você já escolheu essa carta! Tente outra opção.\n");
                                opcao = leitura(0, 5);
                            }
                            if (cartas.getAtual().get(opcao - 1).getCusto() > heroi.getExp()) {
                                atualizaTela(heroi);
                                System.out.println("\nvs\n");
                                inimigoEscolhido.atualiza();
                                System.out.print("\u001B[31m>> Sem XP suficiente para essa carta!\u001B[m");   
                            } else {
                                int dado = cartas.getAtual().get(opcao - 1).usar(heroi, inimigoEscolhido);
                                String nomeCarta = cartas.getAtual().get(opcao - 1).getNome();
                                String texto = cartas.getAtual().get(opcao - 1).usarTexto(heroi, inimigoEscolhido, dado, nomeCarta);
                                heroi.gastarExp(cartas.getAtual().get(opcao - 1).getCusto());
                                cartas.usar(opcao - 1);
                                atualizaTela(heroi);
                                System.out.println("\nvs\n");
                                inimigoEscolhido.atualiza();
                                destaque(">> " + texto);  
                            }
                             

                        }
                    } else {
                        ResultadoAcaoInimigo resultadoAcao = inimigoEscolhido.executarTurno(heroi);
                        heroi.resetarExp();
                        heroi.resetarEscudo();
                        cartas.descartarAtual();
                        cartas.comprarAtual();
                        atualizaTela(heroi);
                        System.out.println("\nvs\n");
                        inimigoEscolhido.atualiza();
                        destaque(">> " + heroi.getNome() + " encerrou o turno! " + resultadoAcao.getMensagemCombate());
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
                return new Inimigo(
                    "Zumbi",
                    20,
                    2,
                    new AcaoInimigo("Arranhão", TipoAcaoInimigo.Ataque, 4, 85, 55),
                    new AcaoInimigo("Mordida", TipoAcaoInimigo.Ataque, 7, 60, 30),
                    new AcaoInimigo("Pele endurecida", TipoAcaoInimigo.Defesa, 1, 100, 15)
                );
            } else if (sorteio == 1) {
                return new Inimigo(
                    "Esqueleto",
                    10,
                    1,
                    new AcaoInimigo("Flecha rápida", TipoAcaoInimigo.Ataque, 5, 90, 60),
                    new AcaoInimigo("Flecha pesada", TipoAcaoInimigo.Ataque, 8, 65, 25),
                    new AcaoInimigo("Postura defensiva", TipoAcaoInimigo.Defesa, 1, 100, 15)
                );
            } else {
                return new Inimigo(
                    "Creeper",
                    20,
                    0,
                    new AcaoInimigo("Investida explosiva", TipoAcaoInimigo.Ataque, 9, 45, 35),
                    new AcaoInimigo("Estouro curto", TipoAcaoInimigo.Ataque, 5, 85, 50),
                    new AcaoInimigo("Carapaça de poeira", TipoAcaoInimigo.Defesa, 1, 100, 15)
                );
            }
        }
        if (fase == 1){
            if (sorteio == 0){
                return new Inimigo(
                    "Blaze",
                    25,
                    3,
                    new AcaoInimigo("Rajada de fogo", TipoAcaoInimigo.Ataque, 9, 75, 55),
                    new AcaoInimigo("Chama intensa", TipoAcaoInimigo.Ataque, 12, 50, 30),
                    new AcaoInimigo("Aura flamejante", TipoAcaoInimigo.Defesa, 2, 100, 15)
                );
            } else if (sorteio == 1){
                return new Inimigo(
                    "Enderman",
                    30,
                    1,
                    new AcaoInimigo("Golpe teleportado", TipoAcaoInimigo.Ataque, 10, 70, 50),
                    new AcaoInimigo("Soco sombrio", TipoAcaoInimigo.Ataque, 13, 50, 30),
                    new AcaoInimigo("Desvio dimensional", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            } else {   
                return new Inimigo(
                    "Esqueleto Whiter",
                    15,
                    8,
                    new AcaoInimigo("Golpe sombrio", TipoAcaoInimigo.Ataque, 10, 80, 55),
                    new AcaoInimigo("Corte perfurante", TipoAcaoInimigo.Ataque, 14, 45, 25),
                    new AcaoInimigo("Ossos reforcados", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            }
        }
        if (fase == 2){
            if (sorteio ==0){
                return new Inimigo(
                    "Whiter",
                    30,
                    8,
                    new AcaoInimigo("Cabeçada de wither", TipoAcaoInimigo.Ataque, 12, 78, 55),
                    new AcaoInimigo("Rajada devastadora", TipoAcaoInimigo.Ataque, 16, 48, 25),
                    new AcaoInimigo("Armadura negra", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            } else if (sorteio == 1){
                return new Inimigo(
                    "Warden",
                    40,
                    5,
                    new AcaoInimigo("Soco tectônico", TipoAcaoInimigo.Ataque, 14, 75, 50),
                    new AcaoInimigo("Impacto ensurdecedor", TipoAcaoInimigo.Ataque, 18, 42, 25),
                    new AcaoInimigo("Pele de pedra", TipoAcaoInimigo.Defesa, 3, 100, 25)
                );
            } else {
                return new Inimigo(
                    "Guardião",
                    30,
                    5,
                    new AcaoInimigo("Garra aquática", TipoAcaoInimigo.Ataque, 11, 82, 55),
                    new AcaoInimigo("Corrente profunda", TipoAcaoInimigo.Ataque, 15, 50, 25),
                    new AcaoInimigo("Escama ancestral", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            }
        }
        return null;
    }

    public static Inimigo criarBossFinal(){
        return new Inimigo(
            "Ender Dragon",
            50,
            10,
            new AcaoInimigo("Sopro dracônico", TipoAcaoInimigo.Ataque, 20, 65, 45),
            new AcaoInimigo("Garras do vazio", TipoAcaoInimigo.Ataque, 14, 85, 35),
            new AcaoInimigo("Escamas ancestrais", TipoAcaoInimigo.Defesa, 4, 100, 20)
        );
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

    public static int leitura(int min, int max) {
        while (true) {
            System.out.print("Digite a opção: ");
            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                if (opcao >= min && opcao <= max) {
                    return opcao;
                } else {
                    System.out.println("\u001B[31mOpção inválida. Tente novamente...\u001B[m\n");
                }
            } else {
                System.out.println("\u001B[31mInteiro inválido. Tente novamente...\u001B[m\n");
                scanner.next();
            }
        }
        
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