package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Estrutura de dados usada para se pegar a árvore geradora mínima de um grafo a
 * partir de um vértice usando o algoritmo de Prim.
 * 
 * Importante salientar que esse algoritmo só funciona para grafos com 2 ou mais
 * vértices.
 */
public class AlgoritmoDePrim {

    private static final int CUSTO_INFINITO = Integer.MAX_VALUE;
    private Vertice inicial;
    private List<Vertice> borda;
    private List<Vertice> incluidos;
    private Map<String, Vertice> vertices;
    private Map<String, Integer> custo;
    private Map<String, Vertice> verticeLigante;
    // private Map<Vertice, Aresta> arestaDeLigacaoAoVertice;
    private Grafo arvoreMinima;

    /** Simples construtor. */
    public AlgoritmoDePrim(Map<String, Vertice> vertices, Vertice inicial) {
        this.vertices = vertices;
        this.inicial = inicial;

        borda = new ArrayList<Vertice>();
        incluidos = new ArrayList<Vertice>();
        custo = new HashMap<String, Integer>();
        verticeLigante = new HashMap<String, Vertice>();
        arvoreMinima = new Grafo();

        iniciaCustosInfinitos();
        rodaAlgoritmo();
    }

    /**
     * Computa custo infinito (aproximado) para todos os vértices do grafo inicial.
     */
    private void iniciaCustosInfinitos() {
        for (String verticeID : vertices.keySet()) {
            custo.put(verticeID, CUSTO_INFINITO);
            verticeLigante.put(verticeID, inicial);
        }
    }

    /** Roda algoritmo de prim para pegar Arvore mínima de vertices passados. */
    private void rodaAlgoritmo() {
        incluidos.add(inicial);
        arvoreMinima.adicionaVertice(inicial.pegaID());
        vertices.remove(inicial.pegaID());

        for (Vertice vizinho : inicial.pegaVizinhos()) {
            borda.add(vizinho);
            custo.put(vizinho.pegaID(), inicial.pegaPeso(vizinho).pegaDistancia());
        }

        while (!borda.isEmpty()) {
            Vertice verticeMenosCustoso = pegaVerticeMenosCustosoDaBorda();
            incluidos.add(verticeMenosCustoso);
            borda.remove(verticeMenosCustoso);
            vertices.remove(verticeMenosCustoso.pegaID());
            Vertice verticeLigadoAoMenosCustoso = this.verticeLigante.get(verticeMenosCustoso.pegaID());
            PesoDaAresta pesoDeLigacao = verticeMenosCustoso.pegaPeso(verticeLigadoAoMenosCustoso);
            arvoreMinima.adicionaAresta(verticeMenosCustoso.pegaID(), verticeLigadoAoMenosCustoso.pegaID(),
                    pesoDeLigacao);

            for (Vertice vizinho : verticeMenosCustoso.pegaVizinhos()) {
                PesoDaAresta pesoProVizinho = verticeMenosCustoso.pegaPeso(vizinho);
                if (vertices.values().contains(vizinho)) {
                    if (custo.get(vizinho.pegaID()) > pesoProVizinho.pegaDistancia()) {
                        custo.put(vizinho.pegaID(), pesoProVizinho.pegaDistancia());
                        if (!borda.contains(vizinho)) {
                            borda.add(vizinho);
                        }
                        verticeLigante.put(vizinho.pegaID(), verticeMenosCustoso);
                    }
                }
            }
        }
    }

    /** Pega vértice menos custoso da borda. */
    private Vertice pegaVerticeMenosCustosoDaBorda() {
        Vertice verticeMenosCustoso = borda.get(0);
        int menorCusto = custo.get(verticeMenosCustoso.pegaID());

        for (Vertice vertice : borda) {
            String idDoAtual = vertice.pegaID();
            int custoDoAtual = custo.get(idDoAtual);

            if (custoDoAtual < menorCusto) {
                verticeMenosCustoso = vertice;
                menorCusto = custoDoAtual;
            }
        }
        return verticeMenosCustoso;
    }

    /** Pega árvore mínima montada pelo algoritmo. */
    public Grafo pegaArvoreMinima() {
        return arvoreMinima;
    }

    // /** Checa se Vertice A é vizinho do Vertice B. */
    // private boolean existeArestaConectando(String idVerticeA, String idVerticeB)
    // {
    // Vertice A = vertices.get(idVerticeA);

    // for (Vertice vizinho : A.pegaVizinhos()) {
    // if (vizinho.pegaID().equals(idVerticeB)) {
    // return true;
    // }
    // }
    // return false;
    // }

    // /**
    // * Classe adicional do algoritmo de prim para representar arestas de um grafo.
    // */
    // private class Aresta {
    // public Vertice A;
    // public Vertice B;
    // public PesoDaAresta peso;

    // /** Simples construtor. */
    // public Aresta(Vertice A, Vertice B, PesoDaAresta peso) {
    // this.A = A;
    // this.B = B;
    // this.peso = peso;
    // }
    // }

}