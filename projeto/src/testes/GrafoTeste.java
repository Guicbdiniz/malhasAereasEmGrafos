package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.DuracaoDeVoo;
import classes.Grafo;
import classes.PesoDaAresta;

/**
 * Testes para a classe Grafo.
 */
public class GrafoTeste {

    private PesoDaAresta pesoTeste;
    private Grafo grafoTeste;

    @Before
    public void setUp() {
        this.pesoTeste = PesoDaAresta.criaPesoDeRota(0, new DuracaoDeVoo(1, 20));
        this.grafoTeste = new Grafo();
    }

    @Test
    public void testaAdicionaVertice() {
        this.grafoTeste.adicionaVertice("A");

        assertEquals(1, this.grafoTeste.pegaNumeroDeVertices());
    }

    @Test
    public void testaAdicionaAresta() {
        this.grafoTeste.adicionaAresta("A", "B", this.pesoTeste);

        assertEquals(2, this.grafoTeste.pegaNumeroDeVertices());
    }

}