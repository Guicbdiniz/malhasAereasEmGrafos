package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Grafo não dirigido.
 */
public class Grafo {

    private int numeroDeVertices;
    private Map<String, Vertice> verticesMap;

    public Grafo() {
        this.numeroDeVertices = 0;
        this.verticesMap = new HashMap<String, Vertice>();
    }

    public Vertice adicionaVertice(String idAdicionado) {
        Vertice verticeAdicionado = new Vertice(idAdicionado);

        this.numeroDeVertices += 1;
        this.verticesMap.put(idAdicionado, verticeAdicionado);
        return verticeAdicionado;
    }

    public Vertice pegaVertice(String id) {
        return this.verticesMap.get(id);
    }

    public void adicionaAresta(String idVerticeA, String idVerticeB, int peso) {
        if (this.verticesMap.get(idVerticeA) == null) {
            this.adicionaVertice(idVerticeA);
        }

        if (this.verticesMap.get(idVerticeB) == null) {
            this.adicionaVertice(idVerticeB);
        }

        this.verticesMap.get(idVerticeA).adicionaVizinho(this.verticesMap.get(idVerticeB), peso);
        this.verticesMap.get(idVerticeB).adicionaVizinho(this.verticesMap.get(idVerticeA), peso);
    }

    public Set<String> pegaIDDosVertices() {
        return this.verticesMap.keySet();
    }

    public int pegaNumeroDeVertices() {
        return this.numeroDeVertices;
    }
}