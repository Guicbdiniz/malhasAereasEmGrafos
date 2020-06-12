package testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.Caminho;
import classes.DuracaoDeVoo;
import classes.GrafoDirigido;
import classes.PesoDaAresta;
import classes.Vertice;

/**
 * Teste para a classe GrafoDirigido.
 */
public class GrafoDirigidoTeste {

    private GrafoDirigido grafoDirigidoTeste;
    private PesoDaAresta pesoTeste;

    @Before
    public void setUp() {
        this.pesoTeste = PesoDaAresta.criaPesoDeRota(0, new DuracaoDeVoo(1, 20));
        this.grafoDirigidoTeste = new GrafoDirigido();
    }

    public void setUpArestasDeMesmoPeso() {
        grafoDirigidoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("A", "H", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("A", "F", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("F", "A", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("E", "F", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("C", "E", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("H", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("C", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "D", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "E", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("E", "C", pesoTeste);
    }

    @Test
    public void testaAdicionaAresta() {
        this.grafoDirigidoTeste.adicionaAresta("A", "B", this.pesoTeste);

        Vertice a = this.grafoDirigidoTeste.pegaVertice("A");
        Vertice b = this.grafoDirigidoTeste.pegaVertice("B");

        assertEquals(0, b.pegaVizinhos().size());
        assertEquals(1, a.pegaVizinhos().size());

    }

    @Test
    public void testaPegaCaminhosEntreVertices() {
        grafoDirigidoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("A", "H", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("A", "F", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("F", "A", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("E", "F", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("C", "E", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("H", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("C", "G", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "D", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("B", "E", pesoTeste);
        grafoDirigidoTeste.adicionaAresta("E", "C", pesoTeste);

        List<Caminho> caminhosEntreAEG = grafoDirigidoTeste.pegaCaminhosEntreVertices("A", "G");
        for (Caminho caminho : caminhosEntreAEG) {
            System.out.println(caminho);
        }

        assertEquals(3, caminhosEntreAEG.size());
    }

    @Test
    public void testaPegaNumeroDeArestas() {
        setUpArestasDeMesmoPeso();
        assertEquals(12, this.grafoDirigidoTeste.pegaNumeroDeArestas());
    }

}