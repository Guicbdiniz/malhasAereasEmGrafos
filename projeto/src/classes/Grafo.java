package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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