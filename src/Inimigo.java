import java.util.Random;

public class Inimigo extends Entidade {
    private static int qtdAcoes = 3;
    private static int escudoMax = 30;
    private static Random RANDOM = new Random();
    private AcaoInimigo[] acoes;
    private AcaoInimigo proximaAcao;

    public Inimigo(String nome, int vida, int escudo, AcaoInimigo... acoesDisponiveis){
        super(nome, vida, escudo, vida, escudo);
        this.acoes = new AcaoInimigo[qtdAcoes];

        for (int i = 0; i < qtdAcoes; i++) {
            this.acoes[i] = acoesDisponiveis[i];
        }
    }

    public int getVida() {
        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

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

    public String atualizaVida() {
        return super.atualizaVida();
    }

    public String atualizaEscudo() {
        if (escudo < 0) {
            escudo = 0;
        }

        int escudoExibicao = Math.min(escudo, escudoMax);
        int blocosCheios = (escudoExibicao * 10) / escudoMax;
        return "ESCUDO: [" + "■".repeat(blocosCheios) + "-".repeat(10 - blocosCheios) + "] " + escudo + "/" + escudoMax;
    }

    public void atualiza() {
        System.out.println("\u001B[31m" + getNome() + " " + atualizaVida() + "\u001B[m | " + "\u001B[31m" + atualizaEscudo() + "\u001B[m\n");
    }

    private boolean escudoNoLimite() {
        return escudo >= escudoMax;
    }


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

    private boolean acertouComPrecisao(int precisao) {
        int sorteio = RANDOM.nextInt(100) + 1;
        return sorteio <= precisao;
    }

    public String anunciarProximaAcao() {
        proximaAcao = escolherAcao();
        if (proximaAcao.getTipo() == TipoAcaoInimigo.Ataque) {
            return " " + getNome() + " pretende usar " + proximaAcao.getNome() + " e causará " + proximaAcao.getValor() + " de dano.";
        }
        int ganhoPrevisto = Math.min(proximaAcao.getValor(), escudoMax - escudo);
        if (ganhoPrevisto < 0) {
            ganhoPrevisto = 0;
        }
        return " " + getNome() + " pretende usar " + proximaAcao.getNome() + " e ganhará " + ganhoPrevisto + " de escudo.";
    }

    public ResultadoAcaoInimigo executarTurno(Heroi heroi) {
        AcaoInimigo acao = proximaAcao;
        if (acao == null) {
            acao = escolherAcao();
        }
        proximaAcao = null;
        boolean acertou = acertouComPrecisao(acao.getPrecisao());

        if (!acertou) {
            return new ResultadoAcaoInimigo(
                acao.getNome(),
                false,
                0,
                0,
                getNome() + " tentou " + acao.getNome() + ", mas errou!"
            );
        }

        if (acao.getTipo() == TipoAcaoInimigo.Ataque) {
            int danoReal = heroi.receberDano(acao.getValor());
            return new ResultadoAcaoInimigo(
                acao.getNome(),
                true,
                danoReal,
                0,
                getNome() + " usou " + acao.getNome() + " e causou " + danoReal + " de dano."
            );
        }

        int escudoAntes = escudo;
        int escudoDepois = Math.min(escudoMax, escudoAntes + acao.getValor());
        int ganhoReal = escudoDepois - escudoAntes;
        escudo = escudoDepois;

        return new ResultadoAcaoInimigo(
            acao.getNome(),
            true,
            0,
            ganhoReal,
            getNome() + " usou " + acao.getNome() + " e ganhou " + ganhoReal + " de escudo."
        );
    }


}
