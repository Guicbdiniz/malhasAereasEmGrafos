package classes;

import excecoes.RotaNaoEVooExcecao;

public class PesoDaAresta {

    private int distancia;
    private DuracaoDeVoo duracao;
    private Horario horario;

    private PesoDaAresta(int distancia, DuracaoDeVoo duracao) {
        this.distancia = distancia;
        this.duracao = duracao;
        this.horario = null;
    }

    private PesoDaAresta(int distancia, DuracaoDeVoo duracao, Horario horario) {
        this.distancia = distancia;
        this.duracao = duracao;
        this.horario = horario;
    }

    public static PesoDaAresta criaPesoDeRota(int distancia, DuracaoDeVoo duracao) {
        return new PesoDaAresta(distancia, duracao);
    }

    public static PesoDaAresta criaPesoDeVoo(int distancia, DuracaoDeVoo duracao, Horario horario) {
        return new PesoDaAresta(distancia, duracao, horario);
    }

    public int pegaDistancia() {
        return distancia;
    }

    public DuracaoDeVoo pegaDuracao() {
        return duracao;
    }

    public Horario pegaHorario() throws RotaNaoEVooExcecao {
        if (horario == null)
            throw new RotaNaoEVooExcecao("Não se deve pegar horario de uma rota.");
        return horario;
    }

}