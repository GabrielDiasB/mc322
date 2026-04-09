package jogo.entidades;
import jogo.batalha.*;
import jogo.efeitos.*;


import java.util.Random;

/**
 * Representa um inimigo da jornada com um conjunto de ações e comportamento próprio de turno.
 */
public class Inimigo extends Entidade {
    /** Número de ações distintas disponíveis para cada inimigo. */
    private static int qtdAcoes = 3;
    /** Limite máximo de escudo exibido e aplicado pelo inimigo. */
    private static int escudoMax = 30;
    /** Gerador aleatório usado para escolha de ação e acerto. */
    private static Random RANDOM = new Random();
    /** Lista fixa de ações disponíveis para o inimigo atual. */
    private AcaoInimigo[] acoes;
    /** Ação sorteada para o próximo turno. */
    private AcaoInimigo proximaAcao;

    /**
     * Cria um inimigo com suas ações configuradas.
     *
     * @param nome nome do inimigo
     * @param vida vida inicial
     * @param escudo escudo inicial
     * @param acoesDisponiveis conjunto de ações que o inimigo pode executar
     */
    public Inimigo(String nome, int vida, int escudo, AcaoInimigo... acoesDisponiveis){
        super(nome, vida, escudo, vida, escudo);
        this.acoes = new AcaoInimigo[qtdAcoes];

        for (int i = 0; i < qtdAcoes; i++) {
            this.acoes[i] = acoesDisponiveis[i];
        }
    }

    /** @return vida atual do inimigo, normalizada para não ficar negativa. */
    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    /** @return vida de referência usada na barra exibida. */
    public int getVidaInicial() {
        return vidaInicial;
    }

    /**
     * Aplica dano respeitando primeiro o escudo do inimigo.
     *
     * @param danoSofrido dano bruto recebido
     * @return dano efetivamente aplicado à vida
     */
    public int receberDano(int danoSofrido){
        int escudoAntes = escudo;
        if (escudo >= danoSofrido) {
            escudo -= danoSofrido;
            return 0;
        }

        int danoReal = danoSofrido - escudoAntes;
        escudo = 0;
        this.vida -= danoReal;
        return danoReal;
    }

    /** @return barra textual de vida do inimigo. */
    public String atualizaVida() {
        return super.atualizaVida();
    }

    /**
     * Formata o escudo do inimigo usando o limite máximo definido para a interface.
     *
     * @return barra textual de escudo
     */
    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }

        int escudoExibicao = Math.min(escudo, escudoMax);
        int blocosCheios = (escudoExibicao * 10) / escudoMax;
        return "ESCUDO: [" + "■".repeat(blocosCheios) + "-".repeat(10 - blocosCheios) + "] " + escudo + "/" + escudoMax;
    }


    /** Exibe o estado atual do inimigo no terminal. */
    public void atualiza() {
        String statusEfeitos = "";
        if (efeitos.isEmpty()) {
            statusEfeitos += "Nenhum\u001B[m";
        } else {
            for (Efeito e : efeitos) {
                statusEfeitos += e.getString() + " ";
            }
            statusEfeitos += "\u001B[m";
        }
        System.out.println("\u001B[31m" + getNome() + " " + atualizaVida() + "\u001B[m | " + "\u001B[31m" + atualizaEscudo() + "\u001B[m | \u001B[31mEFEITOS: " + statusEfeitos + "\u001B[m\n");
    }

    /** @return {@code true} se o escudo já atingiu o limite máximo. */
    private boolean escudoNoLimite() {
        return escudo >= escudoMax;
    }


    /**
     * Escolhe a próxima ação com base no peso de cada habilidade disponível.
     *
     * @return ação sorteada para o turno seguinte
     */
    private AcaoInimigo escolherAcao() {
        AcaoInimigo[] acoesElegiveis = new AcaoInimigo[qtdAcoes];
        int qtdElegiveis = 0;
        int somaChances = 0;

        for (AcaoInimigo acao : acoes) {
            if (escudoNoLimite() && acao.getTipo() == TipoAcaoInimigo.Defesa) {
                continue;
            }
            acoesElegiveis[qtdElegiveis++] = acao;
            somaChances += acao.getChanceEscolha();
        }

        if (qtdElegiveis == 0) {
            return acoes[0];
        }

        int sorteio = RANDOM.nextInt(somaChances) + 1;
        int acumulado = 0;

        for (int i = 0; i < qtdElegiveis; i++) {
            AcaoInimigo acao = acoesElegiveis[i];

            acumulado += acao.getChanceEscolha();
            if (sorteio <= acumulado) {
                return acao;
            }
        }

        return acoesElegiveis[qtdElegiveis - 1];
    }

    /**
     * Verifica se a ação acertou de acordo com sua precisão.
     *
     * @param precisao precisão da ação em porcentagem
     * @return {@code true} quando o teste de precisão passa
     */
    private boolean acertouComPrecisao(int precisao) {
        int sorteio = RANDOM.nextInt(100) + 1;
        return sorteio <= precisao;
    }

    /**
     * Informa ao jogador qual será a próxima ação do inimigo.
     *
     * @return texto descritivo da ação sorteada
     */
    public String anunciarProximaAcao() {
        proximaAcao = escolherAcao();
        if (proximaAcao.getTipo() == TipoAcaoInimigo.Ataque) {
            return " " + getNome() + " pretende usar " + proximaAcao.getNome() + " e causará " + proximaAcao.getValor() + " de dano.";
        } else if (proximaAcao.getTipo() == TipoAcaoInimigo.Defesa) {
            int ganhoPrevisto = Math.min(proximaAcao.getValor(), escudoMax - escudo);
            if (ganhoPrevisto < 0) ganhoPrevisto = 0;
            return " " + getNome() + " pretende usar " + proximaAcao.getNome() + " e ganhará " + ganhoPrevisto + " de escudo.";
        } else {
            return " " + getNome() + " pretende usar " + proximaAcao.getNome() + "!";
        }
    }

    /**
     * Executa a ação previamente anunciada pelo inimigo.
     *
     * @param heroi alvo da ação do inimigo
     * @param combate contexto do combate para eventos e efeitos
     * @return resultado consolidado da ação executada
     */
    public ResultadoAcaoInimigo executarTurno(Heroi heroi, Combate combate) {
        AcaoInimigo acao = proximaAcao;
        if (acao == null) {
            acao = escolherAcao();
        }
        proximaAcao = null;
        boolean acertou = acertouComPrecisao(acao.getPrecisao());
        if (acertou) {
            if (acao.getTipo() == TipoAcaoInimigo.Ataque) {
                combate.notificar(EventoCombate.ATAQUE_INIMIGO);
                
                int forca = 0;
                for (Efeito e : efeitos) {
                    if (e.getNome().equals("Força")) {
                        forca = e.getAcumulos();
                    }
                }

                int danoReal = heroi.receberDano(acao.getValor() + forca);
                return new ResultadoAcaoInimigo(
                    acao.getNome(), true, danoReal, 0, getNome() + " usou " + acao.getNome() + " e causou " + danoReal + " de dano."
                );
                
            } else if (acao.getTipo() == TipoAcaoInimigo.Defesa) {
                int escudoAntes = escudo;
                int escudoDepois = Math.min(escudoMax, escudoAntes + acao.getValor());
                int ganhoReal = escudoDepois - escudoAntes;
                escudo = escudoDepois;

                return new ResultadoAcaoInimigo(
                    acao.getNome(), true, 0, ganhoReal, getNome() + " usou " + acao.getNome() + " e ganhou " + ganhoReal + " de escudo."
                );
                
            } else if (acao.getTipo() == TipoAcaoInimigo.Buff) {
                this.aplicarEfeito(new EfeitoForca(this, acao.getValor()), combate);
                return new ResultadoAcaoInimigo(
                    acao.getNome(), true, 0, 0, getNome() + " usou " + acao.getNome() + " e ganhou Força!"
                );
                
            } else if (acao.getTipo() == TipoAcaoInimigo.Debuff) {
                heroi.aplicarEfeito(new EfeitoVeneno(heroi, acao.getValor()), combate);
                return new ResultadoAcaoInimigo(
                    acao.getNome(), true, 0, 0, getNome() + " usou " + acao.getNome() + " e aplicou Veneno em você!"
                );
            }
        }
        return new ResultadoAcaoInimigo(acao.getNome(), false, 0, 0, getNome() + " usou " + acao.getNome() + " mas errou!");
    }
}
