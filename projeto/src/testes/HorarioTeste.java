package testes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import classes.Horario;

public class HorarioTeste {

    @Test
    public void testaEAntesDe() {
        Horario horarioTeste = new Horario(3, 40);
        Horario comparado = new Horario(5, 30);
        assertTrue(horarioTeste.eAntesDe(comparado));
    }
}