package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Grafo não dirigido.
 * 
 * Sua única responsabilidade é sobre os seus vértices. Suas arestas são
 * tratadas por cada vértice. Seus vértices são salvos em um dicionário (HashMap
 * em Java) que conecta seus nomes identificadores aos vértices em sí.
 * 
 * O número de vértices é salvo em um atributo por motivos de praticidade.
 */
public class Grafo {

    private int numeroDeVertices;
    private Map<String, Vertice> verticesMap;

    /** Simples construtor. */
    public Grafo() {
        this.numeroDeVertices = 0;
        this.verticesMap = new HashMap<String, Vertice>();
    }

    /** Adiciona novo vértice ao grafo a partir de seu nome identificador. */
    public Vertice adicionaVertice(String idAdicionado) {
        Vertice verticeAdicionado = new Vertice(idAdicionado);

        this.numeroDeVertices += 1;
        this.verticesMap.put(idAdicionado, verticeAdicionado);
        return verticeAdicionado;
    }

    /** Pega vértice do grafo a partir de seu identificador. */
    public Vertice pegaVertice(String id) {
        return this.verticesMap.get(id);
    }

    /**
     * Adiciona aresta ao grafo.
     * 
     * @param idVerticeA - nome identificador do primeiro vértice da aresta.
     * @param idVerticeB - nome identificador do segundo vértice da aresta.
     * @param peso       - peso da aresta.
     */
    public void adicionaAresta(String idVerticeA, String idVerticeB, PesoDaAresta peso) {
        if (this.verticesMap.get(idVerticeA) == null) {
            this.adicionaVertice(idVerticeA);
        }

        if (this.verticesMap.get(idVerticeB) == null) {
            this.adicionaVertice(idVerticeB);
        }

        this.verticesMap.get(idVerticeA).adicionaVizinho(this.verticesMap.get(idVerticeB), peso);
        this.verticesMap.get(idVerticeB).adicionaVizinho(this.verticesMap.get(idVerticeA), peso);
    }

    /** Pega nome identificador de todos os vértices do grafo. */
    public Set<String> pegaIDDosVertices() {
        return this.verticesMap.keySet();
    }

    /** Pega número total de vértices do grafo. */
    public int pegaNumeroDeVertices() {
        return this.numeroDeVertices;
    }

    /** Retorna o grafo em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        StringBuilder textoDoGrafo = new StringBuilder();

        for (Vertice vertice : verticesMap.values()) {
            textoDoGrafo.append(vertice.toString() + "\n");
        }

        return textoDoGrafo.toString() + "====================================";
    }
}