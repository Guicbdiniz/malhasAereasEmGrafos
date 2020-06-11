package classes;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
    private Map<String, Integer> distanciasVoo;
    private Map<String, Integer> distanciasViagem;
    private Map<String, Integer> distanciasConexoes;
    private Map<String, Vertice> vertices;
    private Map<Integer, Vertice> pegaVertice;
    private Map<String, Queue<Vertice>> caminhos;
    private List<Vertice> verticesNaoVisitados;
    private List<Integer> menorNaoVisitado;
    private List<Vertice> vizinhos;
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
        distanciasVoo = new HashMap<String, Integer>();
        distanciasViagem = new HashMap<String, Integer>();
        distanciasConexoes = new HashMap<String, Integer>();
        pegaVertice = new HashMap<Integer, Vertice>();
        caminhos = new HashMap<String, Queue<Vertice>>();
        vizinhos = new LinkedList<Vertice>();
        verticesNaoVisitados = new LinkedList<Vertice>();
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
            distanciasVoo.put(verticeID, CUSTO_INFINITO);
            distanciasViagem.put(verticeID, CUSTO_INFINITO);
            caminhos.put(verticeID, new LinkedList<Vertice>());
        }

        for (Vertice vertice : vertices.values()) {
            verticesNaoVisitados.add(vertice);
            caminhos.get(vertice.pegaID()).add(inicial);
        }

        distancias.put(inicial.pegaID(), 0);
        distanciasConexoes.put(inicial.pegaID(), 0);
        distanciasViagem.put(inicial.pegaID(), 0);
        distanciasVoo.put(inicial.pegaID(), 0);
        verticesNaoVisitados.remove(inicial);

        for (Vertice vizinho : inicial.pegaVizinhos()) {
            distanciasVoo.put(vizinho.pegaID(), inicial.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalVoo());
            distancias.put(vizinho.pegaID(), inicial.pegaPeso(vizinho).pegaDistancia());
            distanciasConexoes.put(vizinho.pegaID(), 1);
            caminhos.get(vizinho.pegaID()).add(vizinho);
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa duração do tempo de voo como parâmetro.
     */
    private void rodaAlgoritmoTempoVoo() {
        if (distanciasConexoes.get(destino.pegaID()) == 1) {
            returnCusto = distanciasVoo.get(destino.pegaID());
            returnCaminho = destino.pegaID();
        } else {
            while (verticesNaoVisitados.size() > 0) {
                Vertice verticeAtual = menorNaoVisitadoVoo();
                int pesoProVizinhoAtual = distanciasVoo.get(verticeAtual.pegaID());

                for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                    if (verticesNaoVisitados.contains(vizinho))
                        vizinhos.add(vizinho);
                }
                int tamanhoVizinhos = vizinhos.size();
                for (int i = 0; i < tamanhoVizinhos; i++) {
                    Vertice vizinho = vizinhos.get(0);
                    int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalVoo();
                    if (pesoProVizinhoProx + pesoProVizinhoAtual < distanciasVoo.get(vizinho.pegaID())) {
                        distanciasVoo.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                        caminhos.get(vizinho.pegaID()).add(vizinho);
                    }
                    vizinhos.remove(vizinho);
                }
            }
            returnCaminho = caminhos.get(destino.pegaID()).toString();
            returnCusto = distanciasVoo.get(destino.pegaID());
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro.
     * Usa distância entre vértices como parâmetro.
     */

    private void rodaAlgoritmoDistancia() {
        if (distanciasConexoes.get(destino.pegaID()) == 1) {
            returnCusto = distancias.get(destino.pegaID());
            returnCaminho = destino.pegaID();
        } else {
            while (verticesNaoVisitados.size() > 0) {
                Vertice verticeAtual = menorNaoVisitadoDistancia();
                int pesoProVizinhoAtual = distancias.get(verticeAtual.pegaID());

                for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                    if (verticesNaoVisitados.contains(vizinho))
                        vizinhos.add(vizinho);
                }
                int tamanhoVizinhos = vizinhos.size();
                for (int i = 0; i < tamanhoVizinhos; i++) {
                    Vertice vizinho = vizinhos.get(0);
                    int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDistancia();
                    if (pesoProVizinhoProx + pesoProVizinhoAtual < distancias.get(vizinho.pegaID())) {
                        distancias.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                        caminhos.get(vizinho.pegaID()).add(vizinho);
                    }
                    vizinhos.remove(vizinho);
                }
            }
            returnCaminho = caminhos.get(destino.pegaID()).toString();
            returnCusto = distancias.get(destino.pegaID());
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa duração do tempo da viagem como parâmetro. No momento incompleto devido a
     * necessidade de implementar a função pegaDuracaoTotalViagem
     */

    private void rodaAlgoritmoTempoViagem() {
        if (distanciasConexoes.get(destino.pegaID()) == 1) {
            returnCusto = distanciasViagem.get(destino.pegaID());
            returnCaminho = destino.pegaID();
        } else {
            while (verticesNaoVisitados.size() > 0) {
                Vertice verticeAtual = menorNaoVisitadoViagem();
                int pesoProVizinhoAtual = distanciasViagem.get(verticeAtual.pegaID());

                for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                    if (verticesNaoVisitados.contains(vizinho))
                        vizinhos.add(vizinho);
                }
                int tamanhoVizinhos = vizinhos.size();
                for (int i = 0; i < tamanhoVizinhos; i++) {
                    Vertice vizinho = vizinhos.get(0);
                    int pesoProVizinhoProx = verticeAtual.pegaPeso(vizinho).pegaDuracao().pegaDuracaoTotalViagem();
                    if (pesoProVizinhoProx + pesoProVizinhoAtual < distanciasViagem.get(vizinho.pegaID())) {
                        distanciasViagem.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                        caminhos.get(vizinho.pegaID()).add(vizinho);
                    }
                    vizinhos.remove(vizinho);
                }
            }
            returnCaminho = caminhos.get(destino.pegaID()).toString();
            returnCusto = distanciasViagem.get(destino.pegaID());
        }
    }

    /**
     * Roda algoritmo de Dijkstra para pegar o menor caminho entre um vértice outro
     * Usa número de conexões entre vértices como parâmetro.
     */
    private void rodaAlgoritmoConexoes() {
        if (distanciasConexoes.get(destino.pegaID()) == 1) {
            returnCusto = 1;
            returnCaminho = destino.pegaID();
        } else {
            while (verticesNaoVisitados.size() > 0) {
                Vertice verticeAtual = menorNaoVisitadoConexoes();
                int pesoProVizinhoAtual = distanciasConexoes.get(verticeAtual.pegaID());

                for (Vertice vizinho : verticeAtual.pegaVizinhos()) {
                    if (verticesNaoVisitados.contains(vizinho))
                        vizinhos.add(vizinho);
                }
                int tamanhoVizinhos = vizinhos.size();
                for (int i = 0; i < tamanhoVizinhos; i++) {
                    Vertice vizinho = vizinhos.get(0);
                    int pesoProVizinhoProx = 1;
                    if (pesoProVizinhoProx + pesoProVizinhoAtual < distanciasConexoes.get(vizinho.pegaID())) {
                        System.out.println(pesoProVizinhoProx);
                        distanciasConexoes.put(vizinho.pegaID(), pesoProVizinhoProx + pesoProVizinhoAtual);
                        caminhos.get(vizinho.pegaID()).add(vizinho);
                    }
                    vizinhos.remove(vizinho);
                }
            }
            returnCaminho = caminhos.get(destino.pegaID()).toString();
            returnCusto = distanciasConexoes.get(destino.pegaID());
        }
    }

    /** Pega menor vértice de menor distância ainda não visitado */
    public Vertice menorNaoVisitadoDistancia() {
        Vertice aux;
        int custo;
        menorNaoVisitado.clear();
        for (Vertice vertice : verticesNaoVisitados) {
            custo = distancias.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        aux = pegaVertice.get(menorNaoVisitado.get(0));
        verticesNaoVisitados.remove(aux);
        return aux;
    }

    /** Pega menor vértice de menor tempo de voo ainda não visitado */
    public Vertice menorNaoVisitadoVoo() {
        Vertice aux;
        int custo;
        menorNaoVisitado.clear();
        for (Vertice vertice : verticesNaoVisitados) {
            custo = distanciasVoo.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        aux = pegaVertice.get(menorNaoVisitado.get(0));
        verticesNaoVisitados.remove(aux);
        return aux;
    }

    /** Pega menor vértice de menor número de conxões ainda não visitado */
    public Vertice menorNaoVisitadoConexoes() {
        Vertice aux;
        int custo;
        menorNaoVisitado.clear();
        for (Vertice vertice : verticesNaoVisitados) {
            custo = distanciasConexoes.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        aux = pegaVertice.get(menorNaoVisitado.get(0));
        verticesNaoVisitados.remove(aux);
        return aux;
    }

    /** Pega menor vértice de menor tempo de voo ainda não visitado */
    public Vertice menorNaoVisitadoViagem() {
        Vertice aux;
        int custo;
        menorNaoVisitado.clear();
        for (Vertice vertice : verticesNaoVisitados) {
            custo = distanciasViagem.get(vertice.pegaID());
            menorNaoVisitado.add(custo);
            pegaVertice.put(custo, vertice);
        }

        Collections.sort(menorNaoVisitado);
        aux = pegaVertice.get(menorNaoVisitado.get(0));
        verticesNaoVisitados.remove(aux);
        return aux;
    }

    /** Retorna distancia em forma de texto. */
    public String pegaDistanciaEmTexto() {
        return "O caminho com menor distância partindo de " + inicial.pegaID() + " para " + destino.pegaID() + " é: "
                + returnCaminho + " com custo total de: " + returnCusto;
    }

    /** Retorna número de conxões em forma de texto. */
    public String pegaConexoesEmTexto() {
        return "O caminho com menor número de conexões partindo de " + inicial.pegaID() + " para " + destino.pegaID()
                + " é: " + returnCaminho + " com custo total de: " + returnCusto;
    }

    /** Retorna tempo de voo em forma de texto. */
    public String pegaTempoVooEmTexto() {
        return "O caminho com menor tempo de voo partindo de " + inicial.pegaID() + " para " + destino.pegaID() + " é: "
                + returnCaminho + " com custo total de: " + returnCusto + " minutos, ou " + returnCusto / 60
                + " horas e " + returnCusto % 60 + " minutos.";
    }

    /** Retorna tempo de viagem em forma de texto. */
    public String pegaTempoViagemEmTexto() {
        return "O cmainho com menor tempo de viagem partindo de " + inicial.pegaID() + " para " + destino.pegaID()
                + " é: " + returnCaminho + " com custo total de: " + returnCusto + " minutos, ou " + returnCusto / 60
                + " horas e " + returnCusto % 60 + " minutos.";
    }

}