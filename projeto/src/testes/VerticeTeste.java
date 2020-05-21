package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.Vertice;

/**
 * Testes para a classe Vertice.
 */
public class VerticeTeste {

    private Vertice verticeTeste;
    private Vertice verticeVizinhoTeste;

    @Before
    public void setUp() {
        this.verticeTeste = new Vertice("A");
        this.verticeVizinhoTeste = new Vertice("B");
    }

    @Test
    public void testaToString() {
        assertEquals("Vertice A\nAdjacentes:\n", verticeTeste.toString());
    }

    @Test
    public void testaAdicionarVizinho() {

        this.verticeTeste.adicionaVizinho(verticeVizinhoTeste, 2);
        assertEquals(1, verticeTeste.pegaVizinhos().size());
    }

    @Test
    public void testaPegaPeso() {

        this.verticeTeste.adicionaVizinho(verticeVizinhoTeste, 2);
        assertEquals(2, this.verticeTeste.pegaPeso(this.verticeVizinhoTeste));
    }
}
