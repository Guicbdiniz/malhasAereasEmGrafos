package testes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import classes.AnalisadorDeHorarios;
import classes.DuracaoDeVoo;
import classes.Horario;
import classes.PesoDaAresta;
import classes.Vertice;
import excecoes.RotaNaoEVooExcecao;

public class AnalisadorDeHorariosTeste {

    private Queue<Vertice> sequenciaDeVertices;

    public void setUp(PesoDaAresta AeB, PesoDaAresta BeC, PesoDaAresta CeD, PesoDaAresta DeE) {
        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");
        Vertice E = new Vertice("E");

        A.adicionaVizinho(B, AeB);
        B.adicionaVizinho(C, BeC);
        C.adicionaVizinho(D, CeD);
        D.adicionaVizinho(E, DeE);

        sequenciaDeVertices = new LinkedList<Vertice>();
        sequenciaDeVertices.add(A);
        sequenciaDeVertices.add(B);
        sequenciaDeVertices.add(C);
        sequenciaDeVertices.add(D);
        sequenciaDeVertices.add(E);
    }

    @Test
    public void testaAnalisadorGrande() throws RotaNaoEVooExcecao {
        List<Horario> horarios = new ArrayList<Horario>();
        horarios.add(new Horario(2, 30));
        horarios.add(new Horario(7, 40));
        horarios.add(new Horario(8, 50));
        horarios.add(new Horario(11, 50));
        horarios.add(new Horario(14, 10));
        horarios.add(new Horario(21, 45));
        PesoDaAresta AeB = PesoDaAresta.criaPesoDeVoo(50, new DuracaoDeVoo(1, 20), horarios);

        horarios = new ArrayList<Horario>();
        horarios.add(new Horario(4, 20));
        horarios.add(new Horario(7, 50));
        horarios.add(new Horario(10, 20));
        horarios.add(new Horario(13, 10));
        horarios.add(new Horario(17, 50));
        horarios.add(new Horario(21, 10));
        PesoDaAresta BeC = PesoDaAresta.criaPesoDeVoo(50, new DuracaoDeVoo(0, 10), horarios);

        horarios = new ArrayList<Horario>();
        horarios.add(new Horario(0, 20));
        horarios.add(new Horario(8, 50));
        horarios.add(new Horario(11, 20));
        horarios.add(new Horario(17, 10));
        horarios.add(new Horario(19, 50));
        horarios.add(new Horario(20, 10));
        PesoDaAresta CeD = PesoDaAresta.criaPesoDeVoo(50, new DuracaoDeVoo(1, 5), horarios);

        horarios = new ArrayList<Horario>();
        horarios.add(new Horario(0, 20));
        horarios.add(new Horario(8, 50));
        horarios.add(new Horario(11, 20));
        horarios.add(new Horario(17, 10));
        horarios.add(new Horario(19, 50));
        horarios.add(new Horario(20, 10));
        PesoDaAresta DeE = PesoDaAresta.criaPesoDeVoo(50, new DuracaoDeVoo(2, 5), horarios);

        setUp(AeB, BeC, CeD, DeE);

        Horario achado = AnalisadorDeHorarios.analisaHorarioMaisTardeQueChegaNoDestino(sequenciaDeVertices,
                new Horario(21, 30));

        assertEquals(new Horario(8, 50), achado);
    }
}