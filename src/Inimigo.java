import java.util.Random;

public class Inimigo extends Entidade {
    private static int qtdAcoes = 3;
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
        return super.atualizaEscudo();
    }

    public void atualiza() {
        System.out.println("\u001B[31m" + getNome() + " " + atualizaVida() + "\u001B[m | " + "\u001B[31m" + atualizaEscudo() + "\u001B[m\n");
    }


    private AcaoInimigo escolherAcao() {
        int somaChances = 100;

        int sorteio = RANDOM.nextInt(somaChances) + 1;
        int acumulado = 0;

        for (AcaoInimigo acao : acoes) {

            acumulado += acao.getChanceEscolha();
            if (sorteio <= acumulado) {
                return acao;
            }
        }

        return acoes[0];
    }

    private boolean acertouComPrecisao(int precisao) {
        int sorteio = RANDOM.nextInt(100) + 1;
        return sorteio <= precisao;
    }

    public String anunciarProximaAcao() {
        proximaAcao = escolherAcao();
        if (proximaAcao.getTipo() == TipoAcaoInimigo.Ataque) {
            return getNome() + " usara " + proximaAcao.getNome() + " e causara " + proximaAcao.getValor() + " de dano.";
        }
        return getNome() + " usara " + proximaAcao.getNome() + " e ganhara " + proximaAcao.getValor() + " de escudo.";
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

        escudo += acao.getValor();
        return new ResultadoAcaoInimigo(
            acao.getNome(),
            true,
            0,
            acao.getValor(),
            getNome() + " usou " + acao.getNome() + " e ganhou " + acao.getValor() + " de escudo."
        );
    }


}
