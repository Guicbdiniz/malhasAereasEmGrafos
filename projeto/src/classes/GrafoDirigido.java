package classes;

/**
 * Grafo dirigido.
 * 
 * Sua única diferença ao grafo não dirigido é no modo com eles adicionam uma
 * aresta.
 * 
 * Enquanto o grafo não dirigido adiciona os vértices da aresta à lista de
 * vizinhos de cada vértice da aresta, o grafo dirigido adiciona apenas o
 * vértice destino à lista do vértice de origem.
 */
public class GrafoDirigido extends Grafo {

    /**
     * @param idOrigem  - nome identificador do vértice de origem da aresta.
     * @param idDestino - nome identificador do vértice de destino da aresta.
     * @param peso      - peso da aresta adicionada.
     */
    @Override
    public void adicionaAresta(String idOrigem, String idDestino, PesoDaAresta peso) {
        if (this.pegaVertice(idOrigem) == null) {
            this.adicionaVertice(idOrigem);
        }

        if (this.pegaVertice(idDestino) == null) {
            this.adicionaVertice(idDestino);
        }

        this.pegaVertice(idOrigem).adicionaVizinho(this.pegaVertice(idDestino), peso);
    }

    /** Pega número de arestas do grafo dirigido. */
    @Override
    public int pegaNumeroDeArestas() {
        int numeroTotalDeArestas = 0;
        for (Vertice vertice : verticesMap.values()) {
            numeroTotalDeArestas += vertice.pegaVizinhos().size();
        }
        return numeroTotalDeArestas;
    }
}