package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Analisador de conectividade de grafos não dirigidos.
 */
public class AnalisadorDeConectividade {

    private Map<String, Vertice> vertices;
    private Map<String, Integer> componentesDosVertices;
    private int numeroDeComponentes;
    private boolean grafoEConexo;

    /** Simples construtor */
    public AnalisadorDeConectividade(Map<String, Vertice> vertices) {
        this.vertices = vertices;

        componentesDosVertices = new HashMap<String, Integer>();
        numeroDeComponentes = 0;

        analisaConectividade();
    }

    /**
     * Começa análise de conectividade do grafo.
     * 
     * O algoritmo utilizado é de, basicamente, salvar o número do componente conexo
     * de cada vértice em -1 e ir fazendo uma travessia recursiva em profundidade
     * para atribuir o mesmo número de componente para componentes conexos.
     * 
     */
    private void analisaConectividade() {

        for (String idDoVertice : vertices.keySet()) {
            componentesDosVertices.put(idDoVertice, -1);
        }
        for (String idVertice : vertices.keySet()) {
            if (componentesDosVertices.get(idVertice) == -1) {
                pegaVerticesDoComponente(idVertice, numeroDeComponentes++);
            }
        }

        if (numeroDeComponentes == 1) {
            grafoEConexo = true;
        } else {
            grafoEConexo = false;
        }

    }

    /**
     * Faz uma travessia em profundidade recursivamente, dando o mesmo identificador
     * de componente para cada vértice.
     * 
     * @param idDoVertice    - nome identificador do vértice atual.
     * @param idDoComponente - nome identificador do componente atual.
     */
    private void pegaVerticesDoComponente(String idDoVertice, int idDoComponente) {
        Vertice vertice = vertices.get(idDoVertice);
        componentesDosVertices.put(idDoVertice, idDoComponente);

        for (Vertice vizinho : vertice.pegaVizinhos()) {
            if (componentesDosVertices.get(vizinho.pegaID()) == -1) {
                pegaVerticesDoComponente(vizinho.pegaID(), idDoComponente);
            }
        }
    }

    /** Pega mensagem texto com vértices de cada componente do grafo. */
    private String pegaMensagemDeGrafoNaoConexo() {
        StringBuilder mensagemDeConectividade = new StringBuilder();

        for (int idComponente = 0; idComponente < numeroDeComponentes; idComponente++) {
            mensagemDeConectividade.append((idComponente + 1) + ":\t");
            for (Entry<String, Integer> verticeEComponente : componentesDosVertices.entrySet()) {
                if (verticeEComponente.getValue().intValue() == idComponente) {
                    mensagemDeConectividade.append(verticeEComponente.getKey() + " ");
                }
            }
            mensagemDeConectividade.append("\n");
        }
        return mensagemDeConectividade.toString();
    }

    /** Pega mensagem texto com vértices de corte do grafo. */
    private String pegaMensagemDeGrafoConexo() {
        StringBuilder mensagemDeConectividade = new StringBuilder("Vértices de corte:\n");

        for (Vertice vertice : vertices.values()) {
            if (eVerticeDeCorte(vertice)) {
                mensagemDeConectividade.append(vertice.pegaID() + " ");
            }
        }

        return mensagemDeConectividade.toString();
    }

    /**
     * Checa se vértice é vértice de corte.
     * 
     * O algoritmo usado é de, basicamente, checar se, após remoção do vértice,
     * grafo continua conexo.
     * 
     * O problema é que, graças a natureza de objetos do Java, não existe uma
     * maneira simples de criar uma cópia dos vértices para que um seja removido.
     * Sendo assim, foi necessário criar uma nova estrura de dados (matriz de
     * adjacencia) para cada vértice checado.
     */
    private boolean eVerticeDeCorte(Vertice verticeChecado) {
        int[][] matrizDeAdjacencia = Grafo.pegaMatrizDeAdjacencia(vertices, verticeChecado.pegaID());

        int numeroDeVertices = matrizDeAdjacencia.length;

        int[] componenteDoVertice = new int[numeroDeVertices];
        for (int indice = 0; indice < numeroDeVertices; indice++) {
            componenteDoVertice[indice] = -1;
        }

        int componente = 0;
        for (int indice = 0; indice < numeroDeVertices; indice++) {
            if (componenteDoVertice[indice] == -1) {
                atravessaMatrizEmProfundidade(matrizDeAdjacencia, indice, componente++, componenteDoVertice);
            }
        }

        for (int indice = 0; indice < numeroDeVertices; indice++) {
            if (componenteDoVertice[indice] != 0) {
                return true;
            }
        }

        return false;
    }

    /** Atravessa matriz de adjacencia, checando vértices de mesmo componente. */
    private void atravessaMatrizEmProfundidade(int[][] matrizDeAdjacencia, int indice, int componente,
            int[] componenteDoVertice) {

        componenteDoVertice[indice] = componente;
        for (int vizinho = 0; vizinho < matrizDeAdjacencia.length; vizinho++) {
            if (matrizDeAdjacencia[indice][vizinho] == 1) {
                if (componenteDoVertice[vizinho] == -1) {
                    atravessaMatrizEmProfundidade(matrizDeAdjacencia, vizinho, componente, componenteDoVertice);
                }
            }
        }
    }

    /** Checa se grafo passado é conexo. */
    public boolean grafoEConexo() {
        return grafoEConexo;
    }

    /** Pega mensagem final sobre conectividade do grafo analisado. */
    public String pegaMensagemTextoSobreConectividadeDoGrafo() {
        if (grafoEConexo) {
            return pegaMensagemDeGrafoConexo();
        } else {
            return pegaMensagemDeGrafoNaoConexo();
        }
    }

}