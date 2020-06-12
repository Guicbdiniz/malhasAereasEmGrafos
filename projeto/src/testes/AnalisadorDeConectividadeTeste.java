package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import classes.AnalisadorDeConectividade;
import classes.DuracaoDeVoo;
import classes.Grafo;
import classes.PesoDaAresta;

public class AnalisadorDeConectividadeTeste {

    private Grafo grafoTeste;
    private PesoDaAresta pesoTeste;

    @Before
    public void setUp() {
        grafoTeste = new Grafo();
    }

    @Test
    public void testaAnalisadorGrafoConexo() {
        pesoTeste = PesoDaAresta.criaPesoDeRota(0, new DuracaoDeVoo(1, 20));
        grafoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoTeste.adicionaAresta("A", "C", pesoTeste);
        grafoTeste.adicionaAresta("C", "D", pesoTeste);
        grafoTeste.adicionaAresta("C", "F", pesoTeste);
        grafoTeste.adicionaAresta("C", "E", pesoTeste);

        AnalisadorDeConectividade analisadorDeConectividade = grafoTeste.pegaAnalisadorDeConectivdade();

        assertTrue(analisadorDeConectividade.grafoEConexo());

        System.out.println(analisadorDeConectividade.pegaMensagemTextoSobreConectividadeDoGrafo());
    }

    @Test
    public void testaAnalisadorGrafoNaoConexo() {
        pesoTeste = PesoDaAresta.criaPesoDeRota(0, new DuracaoDeVoo(1, 20));
        grafoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoTeste.adicionaAresta("B", "C", pesoTeste);
        grafoTeste.adicionaAresta("D", "E", pesoTeste);
        grafoTeste.adicionaAresta("E", "F", pesoTeste);
        grafoTeste.adicionaAresta("G", "H", pesoTeste);

        AnalisadorDeConectividade analisadorDeConectividade = grafoTeste.pegaAnalisadorDeConectivdade();

        assertFalse(analisadorDeConectividade.grafoEConexo());

        System.out.println(analisadorDeConectividade.pegaMensagemTextoSobreConectividadeDoGrafo());
    }

}