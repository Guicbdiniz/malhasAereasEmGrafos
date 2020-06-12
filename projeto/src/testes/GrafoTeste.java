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

    @Test
    public void testaPegaArvoreMinima() {

        DuracaoDeVoo duracaoQualquer = new DuracaoDeVoo(1, 20);
        Grafo teste = new Grafo();

        PesoDaAresta pesoAB = PesoDaAresta.criaPesoDeRota(2, duracaoQualquer);
        teste.adicionaAresta("A", "B", pesoAB);

        PesoDaAresta pesoAD = PesoDaAresta.criaPesoDeRota(7, duracaoQualquer);
        teste.adicionaAresta("A", "D", pesoAD);

        PesoDaAresta pesoAC = PesoDaAresta.criaPesoDeRota(3, duracaoQualquer);
        teste.adicionaAresta("A", "C", pesoAC);

        PesoDaAresta pesoBC = PesoDaAresta.criaPesoDeRota(3, duracaoQualquer);
        teste.adicionaAresta("B", "C", pesoBC);

        PesoDaAresta pesoBH = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("B", "H", pesoBH);

        PesoDaAresta pesoCH = PesoDaAresta.criaPesoDeRota(5, duracaoQualquer);
        teste.adicionaAresta("C", "H", pesoCH);

        PesoDaAresta pesoCD = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("C", "D", pesoCD);

        PesoDaAresta pesoCF = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("C", "F", pesoCF);

        PesoDaAresta pesoDE = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("D", "E", pesoDE);

        PesoDaAresta pesoDF = PesoDaAresta.criaPesoDeRota(9, duracaoQualquer);
        teste.adicionaAresta("D", "F", pesoDF);

        PesoDaAresta pesoEF = PesoDaAresta.criaPesoDeRota(3, duracaoQualquer);
        teste.adicionaAresta("E", "F", pesoEF);

        PesoDaAresta pesoFH = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("F", "H", pesoFH);

        PesoDaAresta pesoFG = PesoDaAresta.criaPesoDeRota(1, duracaoQualquer);
        teste.adicionaAresta("F", "G", pesoFG);

        PesoDaAresta pesoHG = PesoDaAresta.criaPesoDeRota(5, duracaoQualquer);
        teste.adicionaAresta("H", "G", pesoHG);

        Grafo arvoreMinima = teste.pegaArvoreGeradoraMinima();

        System.out.println(arvoreMinima);
    }

    @Test
    public void testaPegaMatrizDeAdjacencia() {
        grafoTeste.adicionaAresta("A", "B", pesoTeste);
        grafoTeste.adicionaAresta("A", "C", pesoTeste);
        grafoTeste.adicionaAresta("C", "D", pesoTeste);
        grafoTeste.adicionaAresta("C", "E", pesoTeste);
        grafoTeste.adicionaAresta("C", "F", pesoTeste);

        int[][] matriz = Grafo.pegaMatrizDeAdjacencia(grafoTeste.pegaMapaDeVertices(), "A");

        for (int row = 0; row < grafoTeste.pegaNumeroDeVertices() - 1; row++) {
            for (int col = 0; col < grafoTeste.pegaNumeroDeVertices() - 1; col++) {
                System.out.print(matriz[row][col]);
            }
            System.out.println("");
        }
    }

}