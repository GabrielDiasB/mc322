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
        cartas.addCarta(new CartaDano("Chute Feroz", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaDano("Batida Rápida", "+2 Dano", 1, 2));
        cartas.addCarta(new CartaDano("Soco Mortal", "+3 Dano", 1, 3));
        cartas.addCarta(new CartaEscudo("Reflexo Ágil", "+3 Escudo", 1, 3));
        cartas.addCarta(new CartaEscudo("Esquiva Sagaz", "+2 Escudo", 1, 2));
        cartas.addCarta(new CartaEfeito("Poção de Força", "+2 Força", 2, 2, "Forca"));
        cartas.addCarta(new CartaEfeito("Poção de Veneno", "3-1 Dano", 2, 3, "Veneno"));
        
        System.out.printf("\nSeja muito bem vindo(a), %s!\n", nomeHeroi);
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
                System.out.println("\n\u001B[36m[ 1 ]\u001B[m Explorar o mapa (-1XP ==> +1 Recurso)\n\u001B[36m[ 2 ]\u001B[m Inventário e Crafts\u001B[m\n\u001B[36m[ 3 ]\u001B[m Descansar no acampamento (-1XP ==> +5 vida)\n\u001B[36m[ 4 ]\u001B[m Estou pronto para a noite!\n");
                int selecione = leitura(1, 4);
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
                    destaque(heroi.inventario());
                    System.out.println("\n========================== Inventário ==========================\n");
                    cartas.mostraItens();
                    System.out.println("\n====================== Craftar Novos Itens ======================\n");
                    System.out.println("[ 1 ] " + String.format("%-23s", "Arco e Flecha") + "==> " + String.format("%-12s", "+7 Dano") + String.format("%-20s", "-1 Madeira -1 Lã"));
                    System.out.println("[ 2 ] " + String.format("%-23s", "Espada de madeira") + "==> " + String.format("%-12s", "+8 Dano") + String.format("%-20s", "-2 Madeira"));
                    System.out.println("[ 3 ] " + String.format("%-23s", "Machado de Ferro") + "==> " + String.format("%-12s", "+15 Dano") + String.format("%-20s", "-1 Madeira -2 Ferro"));
                    System.out.println("[ 4 ] " + String.format("%-23s", "Armadura de Diamante") + "==> " + String.format("%-12s", "+9 Escudo") + String.format("%-20s", "-3 Diamante"));
                    System.out.println("[ 5 ] " + String.format("%-23s", "Escudo de Ferro") + "==> " + String.format("%-12s", "+5 Escudo") + String.format("%-20s", "-2 Madeira -1 Ferro"));
                    System.out.println("\n[ 0 ] Voltar para o menu");
                    System.out.println("\n=================================================================");
                    int nova_carta = leitura(0, 5);
                    if (nova_carta == 1) {
                        if (heroi.temLa(1) && heroi.temMadeira(1)) {
                            heroi.gastarLa(1);
                            heroi.gastarMadeira(1);
                            cartas.addCarta(new CartaDano("Arco e Flecha", "+7 Dano", 2, 7));
                            atualizaTela(heroi);
                            destaque("\nArco e Flecha adicionado com sucesso ao seu deque de combate!\n");
                        } else {
                            atualizaTela(heroi);
                            destaque_erro("\nRecursos insuficientes para craftar, explore mais o mapa!\n");
                        }
                        
        
                    } else if (nova_carta == 2) {
                        if (heroi.temMadeira(2)) {
                            heroi.gastarMadeira(2);
                            cartas.addCarta(new CartaDano("Espada de madeira", "+8 Dano", 2, 8));
                            atualizaTela(heroi);
                            destaque("\nEspada de madeira adicionada com sucesso ao seu deque de combate!\n");
                        } else {
                            atualizaTela(heroi);
                            destaque_erro("\nRecursos insuficientes para craftar, explore mais o mapa!\n");
                        }

                    } else if (nova_carta == 3) {
                        if (heroi.temFerro(1) && heroi.temMadeira(1)) {
                            heroi.gastarMadeira(1);
                            heroi.gastarFerro(1);
                            cartas.addCarta(new CartaDano("Machado de Ferro", "+15 Dano", 2, 15));
                            atualizaTela(heroi);
                            destaque("\nMachado de Ferro adicionada com sucesso ao seu deque de combate!\n");
                        } else {
                            atualizaTela(heroi);
                            destaque_erro("\nRecursos insuficientes para craftar, explore mais o mapa!\n");
                        }

                    } else if (nova_carta == 4) {
                        if (heroi.temDiamante(3)) {
                            heroi.gastarDiamante(3);
                            cartas.addCarta(new CartaEscudo("Armadura de Diamante", "+9 Escudo", 2, 9));
                            atualizaTela(heroi);
                            destaque("\nArmadura de Diamante adicionada com sucesso ao seu deque de combate!\n");
                        } else {
                            atualizaTela(heroi);
                            destaque_erro("\nRecursos insuficientes para craftar, explore mais o mapa!\n");
                        }

                    } else if (nova_carta == 5) {
                        if (heroi.temFerro(1) && heroi.temMadeira(2)) {
                            heroi.gastarFerro(1);
                            heroi.gastarMadeira(2);
                            cartas.addCarta(new CartaEscudo("Escudo de Ferro", "+5 Escudo", 1, 5));
                            atualizaTela(heroi);
                            destaque("\nEscudo de Ferro adicionada com sucesso ao seu deque de combate!\n");
                        } else {
                            atualizaTela(heroi);
                            destaque_erro("\nRecursos insuficientes para craftar, explore mais o mapa!\n");
                        }

                    } else {
                        atualizaTela(heroi);
                        continue;
                    }
                    
                
                } else if (selecione == 3) {
                    if (heroi.getExp() >= 1) {
                        heroi.gastarExp(1);
                        heroi.recuperar(5);
                        atualizaTela(heroi);
                        destaque("\n\u001B[33m>> " + heroi.getNome() + " descansou e recuperou +5 vida!\n");
                    } else {
                        atualizaTela(heroi);
                        System.out.println("\n\u001B[31m>> Você não tem experiência suficiente para ganhar mais recursos.\u001B[m");
                    }
                    
                } else if (selecione == 4) {
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

                Combate combate = new Combate(heroi, inimigoEscolhido);

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
                                int dado = cartas.getAtual().get(opcao - 1).usar(heroi, inimigoEscolhido, combate);
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
                        String notificacao = "";
                        notificacao += combate.notificar(EventoCombate.FIM_TURNO_JOGADOR);

                        if (!inimigoEscolhido.estaVivo()) {
                            break;
                        }

                        ResultadoAcaoInimigo resultadoAcao = inimigoEscolhido.executarTurno(heroi, combate);
                        notificacao += combate.notificar(EventoCombate.FIM_TURNO_INIMIGO);
                        
                        heroi.resetarExp();
                        heroi.resetarEscudo();
                        cartas.descartarAtual();
                        cartas.comprarAtual();
                        atualizaTela(heroi);
                        System.out.println("\nvs\n");
                        inimigoEscolhido.atualiza();
                        destaque(">> " + heroi.getNome() + " encerrou o turno! " + resultadoAcao.getMensagemCombate() + notificacao);
                    }
                }
                heroi.limparEfeitos();

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
        int sorteio =  (int) (Math.random() * 4); 
        
        if (fase == 0) {
            if (sorteio == 0) {
                return new Inimigo(
                    "Zumbi",
                    20,
                    2,
                    new AcaoInimigo("Arranhão", TipoAcaoInimigo.Ataque, 4, 85, 50),
                    new AcaoInimigo("Mordida", TipoAcaoInimigo.Ataque, 7, 60, 25),
                    new AcaoInimigo("Carne Podre", TipoAcaoInimigo.Debuff, 2, 90, 25) 
                );
            } else if (sorteio == 1) {
                return new Inimigo(
                    "Esqueleto",
                    10,
                    1,
                    new AcaoInimigo("Flecha rápida", TipoAcaoInimigo.Ataque, 5, 90, 50),
                    new AcaoInimigo("Postura defensiva", TipoAcaoInimigo.Defesa, 1, 100, 25),
                    new AcaoInimigo("Focar Mira", TipoAcaoInimigo.Buff, 1, 100, 25) 
                );
            } else if (sorteio == 2) {
                return new Inimigo(
                    "Creeper",
                    20,
                    0,
                    new AcaoInimigo("Investida explosiva", TipoAcaoInimigo.Ataque, 9, 45, 35),
                    new AcaoInimigo("Estouro curto", TipoAcaoInimigo.Ataque, 5, 85, 50),
                    new AcaoInimigo("Carapaça de poeira", TipoAcaoInimigo.Defesa, 1, 100, 15)
                );
            } else {
                return new Inimigo(
                    "Aranha das Cavernas",
                    15,
                    1,
                    new AcaoInimigo("Salto", TipoAcaoInimigo.Ataque, 5, 90, 40),
                    new AcaoInimigo("Picada Venenosa", TipoAcaoInimigo.Debuff, 3, 85, 40),
                    new AcaoInimigo("Teia Protetora", TipoAcaoInimigo.Defesa, 2, 100, 20)
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
                    new AcaoInimigo("Fúria do End", TipoAcaoInimigo.Buff, 2, 100, 20) 
                );
            } else if (sorteio == 2) {   
                return new Inimigo(
                    "Esqueleto Wither",
                    15,
                    8,
                    new AcaoInimigo("Golpe sombrio", TipoAcaoInimigo.Ataque, 10, 80, 50),
                    new AcaoInimigo("Corte perfurante", TipoAcaoInimigo.Ataque, 14, 45, 20),
                    new AcaoInimigo("Toque do Wither", TipoAcaoInimigo.Debuff, 2, 90, 30) 
                );
            } else {
                return new Inimigo(
                    "Bruxa",
                    25,
                    2,
                    new AcaoInimigo("Poção de Dano", TipoAcaoInimigo.Ataque, 8, 90, 30),
                    new AcaoInimigo("Arremessar Veneno", TipoAcaoInimigo.Debuff, 4, 85, 40),
                    new AcaoInimigo("Poção de Força", TipoAcaoInimigo.Buff, 2, 100, 30) 
                );
            }
        }
        
        if (fase == 2){
            if (sorteio == 0){
                return new Inimigo(
                    "Wither",
                    30,
                    8,
                    new AcaoInimigo("Cabeçada de wither", TipoAcaoInimigo.Ataque, 12, 78, 40),
                    new AcaoInimigo("Rajada devastadora", TipoAcaoInimigo.Ataque, 16, 48, 25),
                    new AcaoInimigo("Névoa de Decomposição", TipoAcaoInimigo.Debuff, 4, 90, 35) 
                );
            } else if (sorteio == 1){
                return new Inimigo(
                    "Warden",
                    40,
                    5,
                    new AcaoInimigo("Soco tectônico", TipoAcaoInimigo.Ataque, 14, 75, 50),
                    new AcaoInimigo("Impacto ensurdecedor", TipoAcaoInimigo.Ataque, 18, 42, 25),
                    new AcaoInimigo("Fúria Cega", TipoAcaoInimigo.Buff, 3, 100, 25) 
                );
            } else if (sorteio == 2) {
                return new Inimigo(
                    "Guardião",
                    30,
                    5,
                    new AcaoInimigo("Garra aquática", TipoAcaoInimigo.Ataque, 11, 82, 55),
                    new AcaoInimigo("Corrente profunda", TipoAcaoInimigo.Ataque, 15, 50, 25),
                    new AcaoInimigo("Escama ancestral", TipoAcaoInimigo.Defesa, 2, 100, 20)
                );
            } else {
                return new Inimigo(
                    "Evoker",
                    25,
                    5,
                    new AcaoInimigo("Presas de Ferro", TipoAcaoInimigo.Ataque, 13, 85, 40),
                    new AcaoInimigo("Totem de Poder", TipoAcaoInimigo.Buff, 2, 100, 30),
                    new AcaoInimigo("Maldição do Illager", TipoAcaoInimigo.Debuff, 3, 90, 30)
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
            new AcaoInimigo("Sopro dracônico", TipoAcaoInimigo.Ataque, 20, 65, 35),
            new AcaoInimigo("Garras do vazio", TipoAcaoInimigo.Ataque, 14, 85, 25),
            new AcaoInimigo("Rugido de Poder", TipoAcaoInimigo.Buff, 2, 100, 20),
            new AcaoInimigo("Bafo Venenoso", TipoAcaoInimigo.Debuff, 4, 80, 20) 
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

    public static void destaque_erro(String texto) {
        System.out.print("\u001B[31m" + texto + "\u001B[m");
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