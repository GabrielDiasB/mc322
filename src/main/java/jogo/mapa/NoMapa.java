package jogo.mapa;

import jogo.eventos.Evento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Representa um nó no mapa que agora contém um Evento (Batalha, Acampamento ou Escolha).
 */
public class NoMapa {
    private final String nome;
    private final int profundidade;
    private final boolean finalDoMapa;
    private final Supplier<Evento> fabricaEvento; // Mudamos de Inimigo para Evento
    private final List<NoMapa> proximos;
    private boolean visitado;

    public NoMapa(String nome, int profundidade, boolean finalDoMapa, Supplier<Evento> fabricaEvento) {
        this.nome = nome;
        this.profundidade = profundidade;
        this.finalDoMapa = finalDoMapa;
        this.fabricaEvento = fabricaEvento;
        this.proximos = new ArrayList<>();
        this.visitado = false;
    }

    public void conectar(NoMapa proximo) {
        proximos.add(proximo);
    }

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
     * Cria o evento associado ao nó (pode ser uma Batalha, Acampamento, etc).
     */
    public Evento criarEvento() {
        if (fabricaEvento == null) return null;
        return fabricaEvento.get();
    }

    public void marcarVisitado() { this.visitado = true; }
    public String getNome() { return nome; }
    public int getProfundidade() { return profundidade; }
    public boolean estaVisitado() { return visitado; }
    public boolean ehFinal() { return finalDoMapa; }
}