package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Vertice
 */
public class Vertice {

    private String id;
    private Map<Vertice, PesoDaAresta> adjacentes;

    public Vertice(String id) {
        this.id = id;
        this.adjacentes = new HashMap<Vertice, PesoDaAresta>();
    }

    @Override
    public String toString() {
        StringBuilder textoDeAdjacentes = new StringBuilder();

        for (Vertice vertice : this.adjacentes.keySet()) {
            textoDeAdjacentes.append("\tVertice " + vertice.id);
        }

        return "Vertice " + this.id + "\nAdjacentes:\n" + textoDeAdjacentes.toString();
    }

    public void adicionaVizinho(Vertice vizinho, PesoDaAresta peso) {
        this.adjacentes.put(vizinho, peso);
    }

    public Set<Vertice> pegaVizinhos() {
        return this.adjacentes.keySet();
    }

    public String pegaID() {
        return this.id;
    }

    public PesoDaAresta pegaPeso(Vertice vizinho) {
        return this.adjacentes.get(vizinho);
    }
}