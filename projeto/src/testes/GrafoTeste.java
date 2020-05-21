package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.Grafo;

/**
 * Testes para a classe Grafo.
 */
public class GrafoTeste {

    private Grafo grafoTeste;

    @Before
    public void setUp() {
        this.grafoTeste = new Grafo();
    }

    @Test
    public void testaAdicionaVertice() {
        this.grafoTeste.adicionaVertice("A");

        assertEquals(1, this.grafoTeste.pegaNumeroDeVertices());
    }

    @Test
    public void testaAdicionaAresta() {
        this.grafoTeste.adicionaAresta("A", "B", 2);

        assertEquals(2, this.grafoTeste.pegaNumeroDeVertices());
    }

}