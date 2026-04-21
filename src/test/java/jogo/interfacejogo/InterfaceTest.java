package jogo.interfacejogo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jogo.entidades.Heroi;

class InterfaceTest {

	@Test
	void leituraDeveIgnorarEntradaInvalidaEAceitarFaixaValida() {
		Interface ui = criarInterface("abc\n9\n2\n");

		int opcao = ui.leitura(1, 3);

		assertEquals(2, opcao);
	}

	@Test
	void deveLerLinhaEConfirmacao() {
		Interface uiLinha = criarInterface("nome do heroi\n");
		assertEquals("nome do heroi", uiLinha.lerLinha("Digite: "));

		Interface uiConfirmacao = criarInterface("S\n");
		assertEquals('s', uiConfirmacao.lerConfirmacao("Continuar? "));
	}

	@Test
	void deveAguardarEnterSemFalhar() {
		Interface ui = criarInterface("\n\n");
		ui.aguardarEnter("Pressione Enter");
		assertTrue(true);
	}

	@Test
	void deveRetornarDesenhoDoHeroiEArtesDeInimigo() {
		Interface ui = criarInterface("\n");

		assertTrue(ui.desenhoHeroi().contains("O"));
		assertTrue(ui.obterArteInimigo("Zumbi").contains("[o]"));
		assertTrue(ui.obterArteInimigo("Ender Dragon").contains("< O >"));
		assertTrue(ui.obterArteInimigo("Desconhecido").contains("(?)"));
	}

	@Test
	void metodosDeExibicaoNaoDevemFalhar() {
		Interface ui = criarInterface("\n");
		Heroi heroi = new Heroi("Alex", 30, 2, 5);

		ui.titulo();
		ui.destaque("ok");
		ui.destaqueErro("erro");
		ui.limpar();
		ui.atualizaTela(heroi);

		assertTrue(true);
	}

	private Interface criarInterface(String conteudoEntrada) {
		ByteArrayInputStream in = new ByteArrayInputStream(conteudoEntrada.getBytes(StandardCharsets.UTF_8));
		return new Interface(new Scanner(in));
	}
}
