package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.Horario;

public class HorarioTeste {

    private List<Horario> listaTeste;

    @Before
    public void setUp() {
        listaTeste = new ArrayList<Horario>();
        listaTeste.add(new Horario(14, 10));
        listaTeste.add(new Horario(7, 15));
        listaTeste.add(new Horario(17, 00));
        listaTeste.add(new Horario(8, 30));
        listaTeste.add(new Horario(20, 50));
    }

    @Test
    public void testaEAntesDe() {
        Horario horarioTeste = new Horario(17, 00);
        Horario comparado = new Horario(7, 15);
        assertTrue("17:00 foi antes de 7:15", comparado.eAntesDe(horarioTeste));

        horarioTeste = new Horario(13, 00);
        comparado = new Horario(14, 30);
        assertFalse("13:00 foi antes de 14:30", comparado.eAntesDe(horarioTeste));

        horarioTeste = new Horario(9, 00);
        comparado = new Horario(8, 30);
        assertTrue("9:00 foi antes de 8:30", comparado.eAntesDe(horarioTeste));

        horarioTeste = new Horario(1, 00);
        comparado = new Horario(9, 30);
        assertFalse("9:30 foi antes de 1:00", comparado.eAntesDe(horarioTeste));

        horarioTeste = new Horario(23, 00);
        comparado = new Horario(22, 30);
        assertTrue("23:00 foi antes de 22:30", comparado.eAntesDe(horarioTeste));

    }

    @Test
    public void testaPegaHorarioMaisCedo() {
        assertEquals(new Horario(7, 15), Horario.pegaHorarioMaisCedo(listaTeste));
    }

    @Test
    public void testaPegaSubListaDeHorariosPosLimite() {
        Horario limite = new Horario(9, 00);
        List<Horario> subLista = Horario.pegaSubListaDeHorariosPosLimite(listaTeste, limite);
        assertEquals(3, subLista.size());
    }

    @Test
    public void testaPegaHorarioMaisCedoDepoisDeLimite() {
        Horario limite = new Horario(9, 00);
        Horario maisCedo = Horario.pegaHorarioMaisCedoDepoisDeLimite(listaTeste, limite);
        assertEquals("Limite 9 falhou", new Horario(14, 10), maisCedo);

        listaTeste.add(new Horario(4, 10));
        limite = new Horario(2, 00);
        maisCedo = Horario.pegaHorarioMaisCedoDepoisDeLimite(listaTeste, limite);
        assertEquals("Limite 2 falhou", new Horario(4, 10), maisCedo);

        limite = new Horario(19, 00);
        maisCedo = Horario.pegaHorarioMaisCedoDepoisDeLimite(listaTeste, limite);
        assertEquals("Limite 19 falhou", new Horario(20, 50), maisCedo);
    }
}