package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Vértice de um grafo.
 * 
 * Devido a especifidade do peso da ligação de cada vertice, essa classe acaba
 * sendo útil apenas no contexto desse projeto.
 * 
 * A responsabilidade de cada vértice e de guardar seu nome identificador e de
 * tratar das suas ligações com vértices vizinhos.
 * 
 * As ligações com os vértices vizinhos (arestas) foram desenvolvidas na forma
 * de um dicionário (HashMap, em Java). Ele liga o vértice vizinho com o peso
 * para acessa-lo.
 */
public class Vertice {

    private String id;
    private Map<Vertice, PesoDaAresta> adjacentes;

    /** Simples construtor. */
    public Vertice(String id) {
        this.id = id;
        this.adjacentes = new HashMap<Vertice, PesoDaAresta>();
    }

    /**
     * Adiciona vizinho (a partir de uma aresta) com seu peso de acesso.
     */
    public void adicionaVizinho(Vertice vizinho, PesoDaAresta peso) {
        this.adjacentes.put(vizinho, peso);
    }

    /**
     * Pega todos os vizinhos do vértice.
     */
    public Set<Vertice> pegaVizinhos() {
        return this.adjacentes.keySet();
    }

    /** Pega nome identificador do vértice. */
    public String pegaID() {
        return this.id;
    }

    /** Pega o peso da aresta que liga o vértice ao vizinho passado. */
    public PesoDaAresta pegaPeso(Vertice vizinho) {
        return this.adjacentes.get(vizinho);
    }

    /** Retorna o vértice em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        StringBuilder textoDeAdjacentes = new StringBuilder();

        for (Vertice vertice : this.adjacentes.keySet()) {
            textoDeAdjacentes.append("Vertice " + vertice.id + "\n");
            textoDeAdjacentes.append(this.pegaPeso(vertice).toString());
        }

        return "Vertice " + this.id + "\nAdjacentes:\n" + textoDeAdjacentes.toString()
                + "------------------------------------";
    }
}