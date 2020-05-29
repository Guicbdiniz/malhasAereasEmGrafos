package testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.Caminho;
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

    public void setUpArestasDeMesmoPeso() {
        grafoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoTeste.adicionaAresta("A", "F", pesoTeste);
        grafoTeste.adicionaAresta("A", "H", pesoTeste);
        grafoTeste.adicionaAresta("B", "D", pesoTeste);
        grafoTeste.adicionaAresta("F", "E", pesoTeste);
        grafoTeste.adicionaAresta("E", "C", pesoTeste);
        grafoTeste.adicionaAresta("B", "G", pesoTeste);
        grafoTeste.adicionaAresta("H", "G", pesoTeste);
        grafoTeste.adicionaAresta("G", "C", pesoTeste);
        grafoTeste.adicionaAresta("B", "E", pesoTeste);
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

    @Test
    public void testaPegaCaminhosEntreVertices() {
        grafoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoTeste.adicionaAresta("A", "F", pesoTeste);
        grafoTeste.adicionaAresta("A", "H", pesoTeste);
        grafoTeste.adicionaAresta("B", "D", pesoTeste);
        grafoTeste.adicionaAresta("F", "E", pesoTeste);
        grafoTeste.adicionaAresta("E", "C", pesoTeste);
        grafoTeste.adicionaAresta("B", "G", pesoTeste);
        grafoTeste.adicionaAresta("H", "G", pesoTeste);
        grafoTeste.adicionaAresta("G", "C", pesoTeste);
        grafoTeste.adicionaAresta("B", "E", pesoTeste);

        List<Caminho> caminhosEntreAEG = grafoTeste.pegaCaminhosEntreVertices("A", "G");

        assertEquals(5, caminhosEntreAEG.size());
    }

    @Test
    public void testaPegaNumeroDeArestas() {
        setUpArestasDeMesmoPeso();
        assertEquals(10, this.grafoTeste.pegaNumeroDeArestas());
    }

}