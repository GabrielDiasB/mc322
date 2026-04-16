package jogo.mapa;

import jogo.entidades.Inimigo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Representa um no do mapa da campanha.
 */
public class NoMapa {
	private final String nome;
	private final int profundidade;
	private final boolean finalDoMapa;
	private final Supplier<Inimigo> fabricaInimigo;
	private final List<NoMapa> proximos;
	private boolean visitado;

	/**
	 * Cria um no do mapa.
	 *
	 * @param nome nome exibido para o jogador
	 * @param profundidade profundidade do no na arvore
	 * @param finalDoMapa indica se este e o no final
	 * @param fabricaInimigo funcao para criar o inimigo deste no
	 */
	public NoMapa(String nome, int profundidade, boolean finalDoMapa, Supplier<Inimigo> fabricaInimigo) {
		this.nome = nome;
		this.profundidade = profundidade;
		this.finalDoMapa = finalDoMapa;
		this.fabricaInimigo = fabricaInimigo;
		this.proximos = new ArrayList<>();
		this.visitado = false;
	}

	/**
	 * Conecta este no a um proximo no possivel.
	 *
	 * @param proximo proximo no alcancavel
	 */
	public void conectar(NoMapa proximo) {
		proximos.add(proximo);
	}

	/**
	 * Lista os nos adjacentes ainda nao visitados.
	 *
	 * @return lista de vizinhos disponiveis
	 */
	public List<NoMapa> getProximosNaoVisitados() {
		List<NoMapa> disponiveis = new ArrayList<>();
		for (NoMapa no : proximos) {
			if (!no.estaVisitado()) {
				disponiveis.add(no);
			}
		}
		return Collections.unmodifiableList(disponiveis);
	}

	/**
	 * Cria o inimigo associado ao no.
	 *
	 * @return inimigo para esta batalha, ou null se nao houver batalha no no
	 */
	public Inimigo criarInimigo() {
		if (fabricaInimigo == null) {
			return null;
		}
		return fabricaInimigo.get();
	}

	/** Marca o no como visitado. */
	public void marcarVisitado() {
		this.visitado = true;
	}

	/**
	 * @return nome do no
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return profundidade na arvore
	 */
	public int getProfundidade() {
		return profundidade;
	}

	/**
	 * @return true se ja foi visitado
	 */
	public boolean estaVisitado() {
		return visitado;
	}

	/**
	 * @return true quando o no representa o fim da campanha
	 */
	public boolean ehFinal() {
		return finalDoMapa;
	}
}
