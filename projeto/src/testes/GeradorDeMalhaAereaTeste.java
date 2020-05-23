package testes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.DuracaoDeVoo;
import classes.GeradorDeMalhaAerea;
import classes.Horario;
import classes.MalhaAerea;

public class GeradorDeMalhaAereaTeste {

    private MalhaAerea malhaTeste;
    private static final String NOME_DO_ARQUIVO_TESTE = "arquivoTeste.txt";

    @Before
    public void setUp() {
        malhaTeste = GeradorDeMalhaAerea.geraMalhaAerea(NOME_DO_ARQUIVO_TESTE);
    }

    @Test
    public void testePegaListaDeHorariosDoVoo() {
        List<Horario> esperado = new ArrayList<Horario>();
        esperado.add(new Horario(20, 15));
        esperado.add(new Horario(1, 40));
        esperado.add(new Horario(4, 30));

        List<String> horariosEmTexto = new ArrayList<String>();
        horariosEmTexto.add("20:15");
        horariosEmTexto.add("01:40");
        horariosEmTexto.add("04:30");

        List<Horario> real = GeradorDeMalhaAerea.pegaListaDeHorariosDoVoo(horariosEmTexto);

        assertArrayEquals(esperado.toArray(), real.toArray());
    }

    @Test
    public void testePegaDuracaoEmTexto() {
        assertEquals(new DuracaoDeVoo(12, 40), GeradorDeMalhaAerea.pegaDuracaoEmTexto("12:40"));
    }

    @Test
    public void testeNumeroDeVerticesGerador() {
        assertEquals("Número de vértices das rotas não bate com os esperado", 3,
                malhaTeste.pegaRotas().pegaNumeroDeVertices());
        assertEquals("Número de vértices dos voos não bate com os esperado", 3,
                malhaTeste.pegaVoos().pegaNumeroDeVertices());
    }

    @Test
    public void testeDeImpressaoDaMalha() {
        System.out.println(this.malhaTeste.toString());
    }
}