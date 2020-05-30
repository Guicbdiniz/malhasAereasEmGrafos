package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.DuracaoDeVoo;
import classes.PesoDaAresta;
import classes.Vertice;

/**
 * Testes para a classe Vertice.
 */
public class VerticeTeste {

    private PesoDaAresta pesoTeste;
    private Vertice verticeTeste;
    private Vertice verticeVizinhoTeste;

    @Before
    public void setUp() {
        this.verticeTeste = new Vertice("A");
        this.verticeVizinhoTeste = new Vertice("B");
        this.pesoTeste = PesoDaAresta.criaPesoDeRota(3, new DuracaoDeVoo(1, 20));
    }

    @Test
    public void testaToString() {
        assertEquals("Vertice A\nAdjacentes:\n", verticeTeste.toString());
    }

    @Test
    public void testaAdicionarVizinho() {
        this.verticeTeste.adicionaVizinho(verticeVizinhoTeste, this.pesoTeste);
        assertEquals(1, verticeTeste.pegaVizinhos().size());
    }

    @Test
    public void testaPegaPeso() {

        this.verticeTeste.adicionaVizinho(verticeVizinhoTeste, this.pesoTeste);
        assertEquals(2, this.verticeTeste.pegaPeso(this.verticeVizinhoTeste));
    }

    @Test
    public void testaPegaVizinhoDeMenorDistancia() {
        Vertice vizinho1 = new Vertice("C");
        Vertice vizinho2 = new Vertice("D");
        PesoDaAresta peso1 = PesoDaAresta.criaPesoDeRota(50, new DuracaoDeVoo(1, 30));
        PesoDaAresta peso2 = PesoDaAresta.criaPesoDeRota(100, new DuracaoDeVoo(2, 5));

        verticeTeste.adicionaVizinho(verticeVizinhoTeste, pesoTeste);
        verticeTeste.adicionaVizinho(vizinho1, peso1);
        verticeTeste.adicionaVizinho(vizinho2, peso2);

        assertEquals(new Vertice("B").pegaID(), verticeTeste.pegaVizinhoDeMenorDistancia().pegaID());
    }
}
