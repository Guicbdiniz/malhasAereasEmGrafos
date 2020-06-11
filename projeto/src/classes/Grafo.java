package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

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
    protected Map<String, Vertice> verticesMap;

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

    /**
     * Pega lista de caminhos possíveis de um vértice inicial do grafo a outro
     * vértice final.
     * 
     * @param idVerticeInicial - vértice inicial do caminho.
     * @param idVerticeFinal   - vértice final do caminho.
     * @return lista de caminhos possíveis.
     */
    public List<Caminho> pegaCaminhosEntreVertices(String idVerticeInicial, String idVerticeFinal) {

        Vertice verticeInicial = verticesMap.get(idVerticeInicial);
        Vertice verticeFinal = verticesMap.get(idVerticeFinal);
        List<Caminho> caminhosEntreVertices = new ArrayList<Caminho>();

        for (Vertice vizinhoAoInicial : verticeInicial.pegaVizinhos()) {
            Stack<Vertice> pilhaDeCaminho = new Stack<Vertice>();
            pilhaDeCaminho.add(verticeInicial);
            pilhaDeCaminho.add(vizinhoAoInicial);

            pegaCaminhosRecursivamente(caminhosEntreVertices, verticeFinal, pilhaDeCaminho);
        }

        return caminhosEntreVertices;
    }

    /**
     * Pega caminhos para vértice final recursivamente a partir de pilha de caminho
     * atual.
     * 
     * A idéia e pegar todos os caminhos a partir dos vizinhos do vértice que está
     * no topo da pilha.
     * 
     * Se o atual for o vértice final, o caminho deve ser adicionado aos caminhos
     * achados.
     * 
     * @param caminhosAchados - caminhos achados até então.
     * @param verticeFinal    - vértice final dos caminhos.
     * @param caminhoAtual    - pilha de vértices do caminho atual.
     */
    private void pegaCaminhosRecursivamente(List<Caminho> caminhosAchados, Vertice verticeFinal,
            Stack<Vertice> caminhoAtual) {

        Vertice atual = caminhoAtual.peek();

        if (atual.equals(verticeFinal)) {
            caminhosAchados.add(Caminho.pegaCaminhoDePilha(caminhoAtual));
        }

        for (Vertice vizinho : atual.pegaVizinhos()) {
            if (!caminhoAtual.contains(vizinho)) {
                caminhoAtual.add(vizinho);
                pegaCaminhosRecursivamente(caminhosAchados, verticeFinal, caminhoAtual);
            }
        }

        caminhoAtual.pop();
    }

    /** Pega número de arestas do grafo. */
    public int pegaNumeroDeArestas() {
        int numeroDeArestasTotal = 0;

        for (Vertice vertice : verticesMap.values()) {
            numeroDeArestasTotal += vertice.pegaVizinhos().size();
        }
        return numeroDeArestasTotal / 2;
    }

    /**
     * Pega árvore geradora mínima do grafo em relação à distância entre aeroportos.
     * 
     * Foi usado o algoritmo de Prim.
     */
    public Grafo pegaArvoreGeradoraMinima() {
        int contador = 0;
        Vertice inicial = null;

        for (Vertice vertice : verticesMap.values()) {
            if (contador == 0) {
                inicial = vertice;
                contador++;
            }
        }
        AlgoritmoDePrim algoritmoDePrim = new AlgoritmoDePrim(verticesMap, inicial);

        return algoritmoDePrim.pegaArvoreMinima();
    }

    /**
     * Pega o menor custo entre dois vértices (origem e destino) usando distância como parâmetro.
     */
    public String pegaDistanciaMinima ( String idInicial, String idDestino) {
        AlgoritmoDeDijkstra algoritmoDeDijkstra = new AlgoritmoDeDijkstra(verticesMap, idInicial, idDestino);
        algoritmoDeDijkstra.distanciaMinima();
        return algoritmoDeDijkstra.pegaDistanciaEmTexto();
    }

     /**
     * Pega o menor custo entre dois vértices (origem e destino) usando distância como parâmetro.
     */
    public String pegaConexoesMinima ( String idInicial, String idDestino) {
        AlgoritmoDeDijkstra algoritmoDeDijkstra = new AlgoritmoDeDijkstra(verticesMap, idInicial, idDestino);
        algoritmoDeDijkstra.conexoesMinima();
        return algoritmoDeDijkstra.pegaConexoesEmTexto();
    }

     /**
     * Pega o menor custo entre dois vértices (origem e destino) usando distância como parâmetro.
     */
    public String pegaTempoVooMinima ( String idInicial, String idDestino) {
        AlgoritmoDeDijkstra algoritmoDeDijkstra = new AlgoritmoDeDijkstra(verticesMap, idInicial, idDestino);
        algoritmoDeDijkstra.tempoVooMinima();
        return algoritmoDeDijkstra.pegaTempoVooEmTexto();
    }

     /**
     * Pega o menor custo entre dois vértices (origem e destino) usando distância como parâmetro.
     */
    public String pegaTempoViagemMinima ( String idInicial, String idDestino) {
        AlgoritmoDeDijkstra algoritmoDeDijkstra = new AlgoritmoDeDijkstra(verticesMap, idInicial, idDestino);
        algoritmoDeDijkstra.tempoViagemMinima();
        return algoritmoDeDijkstra.pegaTempoViagemEmTexto();
    }

    /**
     * Pega analisador de conectividade do grafo.
     * 
     * Com esse objeto é possível adquirir informações sobre conectividade do grafo.
     */
    public AnalisadorDeConectividade pegaAnalisadorDeConectivdade() {
        return new AnalisadorDeConectividade(verticesMap);
    }

    /** Pega matriz de adjacencia de um conjunto de vértices. */
    public static int[][] pegaMatrizDeAdjacencia(Map<String, Vertice> vertices) {
        int numeroDeVertices = vertices.size();
        Map<String, Integer> indiceDeCadaVertice = new HashMap<String, Integer>();

        int indice = 0;
        for (String idVertice : vertices.keySet()) {
            indiceDeCadaVertice.put(idVertice, indice++);
        }

        int[][] matrizDeAdjacencia = new int[numeroDeVertices][numeroDeVertices];
        for (int row = 0; row < numeroDeVertices; row++) {
            for (int col = 0; col < numeroDeVertices; col++) {
                matrizDeAdjacencia[row][col] = 0;
            }
        }

        for (Entry<String, Vertice> vertice : vertices.entrySet()) {
            int indiceDoVertice = indiceDeCadaVertice.get(vertice.getKey());

            for (Vertice vizinho : vertice.getValue().pegaVizinhos()) {
                int indiceDoVizinho = indiceDeCadaVertice.get(vizinho.pegaID());
                matrizDeAdjacencia[indiceDoVertice][indiceDoVizinho] = 1;
            }
        }

        return matrizDeAdjacencia;
    }

    /**
     * Pega matriz de adjacência de um conjunto de vértices com a remoção de um
     * vértice.
     */
    public static int[][] pegaMatrizDeAdjacencia(Map<String, Vertice> vertices, String removido) {
        Map<String, Vertice> verticesCopia = new HashMap<String, Vertice>(vertices);

        verticesCopia.remove(removido);

        int numeroDeVertices = verticesCopia.size();
        Map<String, Integer> indiceDeCadaVertice = new HashMap<String, Integer>();

        int indice = 0;
        for (String idVertice : verticesCopia.keySet()) {
            indiceDeCadaVertice.put(idVertice, indice++);
        }

        int[][] matrizDeAdjacencia = new int[numeroDeVertices][numeroDeVertices];
        for (int row = 0; row < numeroDeVertices; row++) {
            for (int col = 0; col < numeroDeVertices; col++) {
                matrizDeAdjacencia[row][col] = 0;
            }
        }

        for (Entry<String, Vertice> vertice : verticesCopia.entrySet()) {
            int indiceDoVertice = indiceDeCadaVertice.get(vertice.getKey());

            for (Vertice vizinho : vertice.getValue().pegaVizinhos()) {
                if (!vizinho.pegaID().equals(removido)) {
                    int indiceDoVizinho = indiceDeCadaVertice.get(vizinho.pegaID());
                    matrizDeAdjacencia[indiceDoVertice][indiceDoVizinho] = 1;
                }
            }
        }

        return matrizDeAdjacencia;
    }

    /** Pega mapa de vértices do grafo. */
    public Map<String, Vertice> pegaMapaDeVertices() {
        return verticesMap;
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