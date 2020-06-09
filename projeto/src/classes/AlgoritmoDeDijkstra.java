package classes;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Estrutura de dados usada para se pegar a menor distância entre um vértice e
 * outro utilizando o algoritmo de Dijsktra
 * 
 * número de conexões, distância total percorrida, tempo total de voo, duração
 * total da viagem (considerando-se que pode haver esperas nas conexões. Nesse
 * caso, utilize o primeiro horário de voo possível).
 */
public class AlgoritmoDeDijkstra {

    private static final int CUSTO_INFINITO = Integer.MAX_VALUE;
    private Map<String, Integer> distancias;
    private Map<String, Vertice> vertices;
    private Map<Integer, Vertice> pegaVertice;
    private Map<String, Queue<Vertice>> caminhos;
    private List<Vertice> verticesNaoVistados;
    private List<Integer> menorNaoVisitado;
    private Stack<Vertice> vizinhos;
    private Vertice inicial;
    private Vertice destino;
    private String returnCaminho;
    private int returnCusto;

    /** Simples construtor */
    public AlgoritmoDeDijkstra(Map<String, Vertice> vertices, String idInicial, String idDestino) {
        this.vertices = vertices;
        inicial = vertices.get(idInicial);
        destino = vertices.get(idDestino);
        distancias = new HashMap<String, Integer>();
        pegaVertice = new HashMap<Integer, Vertice>();
        caminhos = new HashMap<String, Queue<Vertice>>();
        vizinhos = new Stack<Vertice>();
        verticesNaoVistados = new LinkedList<Vertice>();
        menorNaoVisitado = new LinkedList<Integer>();

        inicializacao();
        rodaAlgoritmo();
    }

    /** Seta valores de distâncias não conhecidas para infinito. */
    private void inicializacao() {
        for (String verticeID : vertices.keySet()) {
            distancias.put(verticeID, CUSTO_INFINITO);
            caminhos.put(verticeID, new LinkedList<Vertice>());
        }

        for (Vertice vertice : vertices.values()) {
            verticesNaoVistados.add(vertice);
            caminhos.get(vertice.pegaID()).add(vertice);
        }


        distancias.put(inicial.pegaID(), 0);
        verticesNaoVistados.remove(inicial);

        for (Vertice vizinho : inicial.pegaVizinhos()) {
            distancias.put(vizinho.pegaID(), inicial.pegaPeso(vizinho).pegaDistancia());
            caminhos.get(vizinho.pegaID()).add(vizinho);
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro.
     */
    private void rodaAlgoritmo() {
        Vertice verticeAtual = menorNaoVisitado();
        int pesoProVizinhoAtual = verticeAtual.pegaPeso(inicial).pegaDistancia();
        verticesNaoVistados.remove(verticeAtual);

        while (verticesNaoVistados.size() > 1) {
            for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                if (verticesNaoVistados.contains(vizinho))
                    vizinhos.add(vizinho);
            }

            for (Vertice vizinho : vizinhos) {
                int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDistancia();
                if (pesoProVizinhoProx + pesoProVizinhoAtual < distancias.get(vizinho.pegaID())) {
                    distancias.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                    caminhos.get(vizinho.pegaID()).add(vizinho);
                }         
                verticesNaoVistados.remove(vizinho);
            }
        }
        returnCaminho = caminhos.get(destino.pegaID()).toString();
        returnCusto = distancias.get(destino.pegaID());
    }

    /** Pega menor vértice de menor custo ainda não visitado */
    public Vertice menorNaoVisitado() {
        int custo;
        for (Vertice vertice :  verticesNaoVistados) {
            custo = distancias.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        return pegaVertice.get(menorNaoVisitado.get(0));      
    }

    /**Retorna a resposta em forma de texto. */
    @Override
    public String toString() {
        return "O caminho com menor custo partindo de " + inicial.pegaID() + " para " + destino.pegaID() + " é: "
                + returnCaminho + " com custo total de: " + returnCusto;
    }

}