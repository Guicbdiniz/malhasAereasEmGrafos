package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.GrafoDirigido;
import classes.Vertice;

public class GrafoDirigidoTeste {

    private GrafoDirigido grafoDirigidoTeste;

    @Before
    public void setUp() {
        this.grafoDirigidoTeste = new GrafoDirigido();
    }

    @Test
    public void testaAdicionaAresta() {
        this.grafoDirigidoTeste.adicionaAresta("A", "B", 3);

        Vertice a = this.grafoDirigidoTeste.pegaVertice("A");
        Vertice b = this.grafoDirigidoTeste.pegaVertice("B");

        assertEquals(0, b.pegaVizinhos().size());
        assertEquals(1, a.pegaVizinhos().size());

    }

}