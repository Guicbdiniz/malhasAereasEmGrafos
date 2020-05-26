package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import classes.GeradorDeMalhaAerea;
import classes.Horario;
import classes.MalhaAerea;
import excecoes.RotaNaoEVooExcecao;

public class MalhaAereaTeste {

    private MalhaAerea malhaTeste;

    private void setUpPrimeiroArquivoTeste() {
        malhaTeste = GeradorDeMalhaAerea.geraMalhaAerea("arquivoTeste.txt");
    }

    @Test
    public void testaPegaHorarioDoUltimoVooSemChegarAtrasado() throws RotaNaoEVooExcecao {

        String idDoVerticeOrigem;
        String idDoVerticeDestino;
        Horario limite;

        setUpPrimeiroArquivoTeste();
        limite = new Horario(20, 0);
        idDoVerticeOrigem = "CONFINS";
        idDoVerticeDestino = "SANTOSDUMONT";

        assertEquals("arquivoText.txt falhou", new Horario(9, 00),
                malhaTeste.pegaHorarioDoUltimoVooSemChegarAtrasado(limite, idDoVerticeOrigem, idDoVerticeDestino));

    }
}