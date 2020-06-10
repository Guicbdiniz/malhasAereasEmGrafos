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
    private Map<String, Integer> distanciasConexoes;
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

    /**
     * 
     * @param vertices  - Mapa de vértices
     * @param idInicial - Identificador do vértice de origem
     * @param idDestino - Identificador do vértice de destino
     * @param idFuncao  - Identificador do tipo de custo
     * 
     */
    public AlgoritmoDeDijkstra(Map<String, Vertice> vertices, String idInicial, String idDestino) {
        this.vertices = vertices;
        inicial = vertices.get(idInicial);
        destino = vertices.get(idDestino);
        distancias = new HashMap<String, Integer>();
        distanciasConexoes = new HashMap<String, Integer>();
        pegaVertice = new HashMap<Integer, Vertice>();
        caminhos = new HashMap<String, Queue<Vertice>>();
        vizinhos = new Stack<Vertice>();
        verticesNaoVistados = new LinkedList<Vertice>();
        menorNaoVisitado = new LinkedList<Integer>();

        inicializacao();
    }

    public void distanciaMinima() {
        rodaAlgoritmoDistancia();
    }

    public void tempoVooMinima() {
        rodaAlgoritmoTempoVoo();
    }

    public void tempoViagemMinima() {
        rodaAlgoritmoTempoViagem();
    }

    public void conexoesMinima() {
        rodaAlgoritmoConexoes();
    }

    /** Seta valores de distâncias não conhecidas para infinito. */
    private void inicializacao() {
        for (String verticeID : vertices.keySet()) {
            distancias.put(verticeID, CUSTO_INFINITO);
            distanciasConexoes.put(verticeID, CUSTO_INFINITO);
            caminhos.put(verticeID, new LinkedList<Vertice>());
        }

        for (Vertice vertice : vertices.values()) {
            verticesNaoVistados.add(vertice);
            caminhos.get(vertice.pegaID()).add(vertice);
        }

        distancias.put(inicial.pegaID(), 0);
        verticesNaoVistados.remove(inicial);

        for (Vertice vizinho : inicial.pegaVizinhos()) {
            distancias.put(vizinho.pegaID(), inicial.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalVoo());
            distanciasConexoes.put(vizinho.pegaID(), 1);
            caminhos.get(vizinho.pegaID()).add(vizinho);
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa duração do tempo de voo como parâmetro.
     */
    private void rodaAlgoritmoTempoVoo() {
        Vertice verticeAtual = menorNaoVisitado();
        int pesoProVizinhoAtual = verticeAtual.pegaPeso(inicial).pegaDuracao().pegaDuracaoTotalVoo();
        verticesNaoVistados.remove(verticeAtual);

        while (verticesNaoVistados.size() > 1) {
            for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                if (verticesNaoVistados.contains(vizinho))
                    vizinhos.add(vizinho);
            }

            for (Vertice vizinho : vizinhos) {
                int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalVoo();
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

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro.
     * Usa distância entre vértices como parâmetro.
     */
    private void rodaAlgoritmoDistancia() {
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

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa duração do tempo da viagem como parâmetro.
     * No momento incompleto devido a necessidade de implementar a função pegaDuracaoTotalViagem
     */

    private void rodaAlgoritmoTempoViagem() {
        Vertice verticeAtual = menorNaoVisitado();
        int pesoProVizinhoAtual = verticeAtual.pegaPeso(inicial).pegaDuracao().pegaDuracaoTotalViagem();
        verticesNaoVistados.remove(verticeAtual);

        while (verticesNaoVistados.size() > 1) {
            for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                if (verticesNaoVistados.contains(vizinho))
                    vizinhos.add(vizinho);
            }

            for (Vertice vizinho : vizinhos) {
                int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalVoo();
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

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa número de conexões entre vértices como parâmetro.
     */
    private void rodaAlgoritmoConexoes() {
        Vertice verticeAtual = menorNaoVisitado();
        int pesoProVizinhoAtual = distanciasConexoes.get(inicial.pegaID());
        verticesNaoVistados.remove(verticeAtual);

        while (verticesNaoVistados.size() > 1) {
            for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                if (verticesNaoVistados.contains(vizinho))
                    vizinhos.add(vizinho);
            }

            for (Vertice vizinho : vizinhos) {
                int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDistancia();
                if (pesoProVizinhoProx + pesoProVizinhoAtual < distanciasConexoes.get(vizinho.pegaID())) {
                    distanciasConexoes.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                    caminhos.get(vizinho.pegaID()).add(vizinho);
                }
                verticesNaoVistados.remove(vizinho);
            }
        }
        returnCaminho = caminhos.get(destino.pegaID()).toString();
        returnCusto = distanciasConexoes.get(destino.pegaID());
    }

    /** Pega menor vértice de menor custo ainda não visitado */
    public Vertice menorNaoVisitado() {
        int custo;
        for (Vertice vertice : verticesNaoVistados) {
            custo = distancias.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        return pegaVertice.get(menorNaoVisitado.get(0));
    }

    /** Retorna distancia em forma de texto. */
    public String pegaDistanciaEmTexto() {
        return "O caminho com menor distância partindo de " + inicial.pegaID() + " para " + destino.pegaID() + " é: "
                + returnCaminho + " com custo total de: " + returnCusto;
    }

    /** Retorna número de conxões em forma de texto. */
    public String pegaConexoesEmTxto() {
        return "O caminho com menor número de conexões partindo de " + inicial.pegaID() + "para " + destino.pegaID()
                + "é: " + returnCaminho + "com custo total de: " + returnCusto;
    }

    /** Retorna tempo de voo em forma de texto. */
    public String pegaTempoVooEmTexto() {
        return "O caminho com menor tempo de voo partindo de " + inicial.pegaID() + " para " + destino.pegaID() + " é: "
                + returnCaminho + " com custo total de: " + returnCusto + " minutos, ou " + returnCusto / 60 + " horas e "
                + returnCusto % 60 + " minutos.";
    }

    /** Retorna tempo de viagem em forma de texto. */
    public String pegaTempoViagemEmTexto() {
        return "O cmainho com menor tempo de viagem partindo de " + inicial.pegaID() + " para " + destino.pegaID()
                + " é: " + returnCaminho + " com custo total de: " + returnCusto + " minutos, ou " + returnCusto / 60
                + " horas e " + returnCusto % 60 + " minutos.";
    }

}