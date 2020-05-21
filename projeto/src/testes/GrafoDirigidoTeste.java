package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.DuracaoDeVoo;
import classes.GrafoDirigido;
import classes.PesoDaAresta;
import classes.Vertice;

public class GrafoDirigidoTeste {

    private GrafoDirigido grafoDirigidoTeste;
    private PesoDaAresta pesoTeste;

    @Before
    public void setUp() {
        this.pesoTeste = PesoDaAresta.criaPesoDeRota(0, new DuracaoDeVoo(1, 20));
        this.grafoDirigidoTeste = new GrafoDirigido();
    }

    @Test
    public void testaAdicionaAresta() {
        this.grafoDirigidoTeste.adicionaAresta("A", "B", this.pesoTeste);

        Vertice a = this.grafoDirigidoTeste.pegaVertice("A");
        Vertice b = this.grafoDirigidoTeste.pegaVertice("B");

        assertEquals(0, b.pegaVizinhos().size());
        assertEquals(1, a.pegaVizinhos().size());

    }

}