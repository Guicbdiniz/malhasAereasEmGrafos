package classes;

import excecoes.RotaNaoEVooExcecao;

/**
 * Peso de cada aresta de qualquer grafo de vôos ou rotas.
 * 
 * A diferenciação entre vôos e rotas é feita apenas durante sua construção. Uma
 * rota recebe um valor nulo para o atributo de horário. Se esse atributo tentar
 * ser acessado, uma excessão será lançada.
 * 
 * O peso de uma aresta não pode ser construído por um construtor. Eles são
 * privados. Devem ser usadas um dos métodos estáticos de geração.
 */
public class PesoDaAresta {

    private int distancia;
    private DuracaoDeVoo duracao;
    private Horario horario;

    /** Construtor privado de rota. */
    private PesoDaAresta(int distancia, DuracaoDeVoo duracao) {
        this.distancia = distancia;
        this.duracao = duracao;
        this.horario = null;
    }

    /** Construtor privado de vôo. */
    private PesoDaAresta(int distancia, DuracaoDeVoo duracao, Horario horario) {
        this.distancia = distancia;
        this.duracao = duracao;
        this.horario = horario;
    }

    /** Método gerador de rota. */
    public static PesoDaAresta criaPesoDeRota(int distancia, DuracaoDeVoo duracao) {
        return new PesoDaAresta(distancia, duracao);
    }

    /** Método gerador de vôo. */
    public static PesoDaAresta criaPesoDeVoo(int distancia, DuracaoDeVoo duracao, Horario horario) {
        return new PesoDaAresta(distancia, duracao, horario);
    }

    /** Pega peso de distância da aresta. */
    public int pegaDistancia() {
        return distancia;
    }

    /** Pega peso de duração da aresta. */
    public DuracaoDeVoo pegaDuracao() {
        return duracao;
    }

    /**
     * Pega peso de horário da aresta.
     * 
     * Se o peso de uma rota tentar acessá-lo, uma excessão será lançada.
     */
    public Horario pegaHorario() throws RotaNaoEVooExcecao {
        if (horario == null)
            throw new RotaNaoEVooExcecao("Não se deve pegar horario de uma rota.");
        return horario;
    }

}