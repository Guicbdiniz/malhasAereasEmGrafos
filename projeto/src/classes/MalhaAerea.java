package classes;

import java.util.List;

import excecoes.RotaNaoEVooExcecao;

public class MalhaAerea {
    private Grafo rotas;
    private GrafoDirigido voos;

    public MalhaAerea(Grafo rotas, GrafoDirigido voos) {
        this.rotas = rotas;
        this.voos = voos;
    }

    public Grafo pegaRotas() {
        return rotas;
    }

    public GrafoDirigido pegaVoos() {
        return voos;
    }

    /**
     * Analisa conectividade das rotas da malha aérea, retornando uma mensagem
     * explicando se o grafo de rotas é conexo. Se ele for conexo, também são
     * apresentados seus cut vértices. Se ele não for conexo, são apresentados seus
     * componentes conexos.
     * 
     * @return mensagem com informações da conectividade da malha.
     */
    public String pegaMensagemRelacionadaAConectividade() {
        AnalisadorDeConectividade analisadorDeConectividade = rotas.pegaAnalisadorDeConectivdade();

        StringBuilder textoDeConectividade = new StringBuilder();

        if (analisadorDeConectividade.grafoEConexo()) {
            textoDeConectividade.append("É possível atingir, a partir de um aeroporto, todos os outros\n");
        } else {
            textoDeConectividade.append("Não é possível atingir, a partir de qualquer aeroporto, todos os outros\n");
        }

        return textoDeConectividade.toString();
    }

    /**
     * Pega o horário do último vôo que pode se pegar no aeroporto de origem em uma
     * rota para o aeroporto de destino que seja possível de ser feita antes do
     * horário limite.
     * 
     * @param limite           - horário limite para se chegar no destino.
     * @param idVerticeOrigem  - nome de identificação do aeroporto de origem.
     * @param idVerticeDestino - nome de identificação do aeroporto de destino.
     * @return horário do último vôo possível.
     * @throws RotaNaoEVooExcecao
     */
    public Horario pegaHorarioDoUltimoVooSemChegarAtrasado(Horario limite, String idVerticeOrigem,
            String idVerticeDestino) throws RotaNaoEVooExcecao {

        List<Caminho> caminhosEntreVertices = voos.pegaCaminhosEntreVertices(idVerticeOrigem, idVerticeDestino);
        Horario horarioDoUltimoVoo = null;

        for (Caminho caminho : caminhosEntreVertices) {
            Horario horarioDoVooMaisTardeDeSaida = caminho.pegaHorarioMaisTardeDeSaida(limite);
            if (horarioDoVooMaisTardeDeSaida != null) {
                if (horarioDoUltimoVoo == null) {
                    horarioDoUltimoVoo = horarioDoVooMaisTardeDeSaida;
                } else if (horarioDoUltimoVoo.eAntesDe(horarioDoVooMaisTardeDeSaida)) {
                    horarioDoUltimoVoo = horarioDoVooMaisTardeDeSaida;
                }
            }
        }
        return horarioDoUltimoVoo;
    }

    /**
     * Pega empresa aérea de carga com frota de aeronaves que farão voos de ida e
     * volta entre dois aeroportos.
     * 
     * A empresa deve alcançar todos os vértices do grafo.
     * 
     * @return empresa aérea.
     */
    public EmpresaAereaDeCarga pegaEmpresaDeCaminhoMinimo() {
        Grafo arvoreGeradoraMinimaDasRotas = rotas.pegaArvoreGeradoraMinima();
        int numeroDeArestasDasRotas = arvoreGeradoraMinimaDasRotas.pegaNumeroDeArestas();

        return new EmpresaAereaDeCarga(numeroDeArestasDasRotas, arvoreGeradoraMinimaDasRotas);
    }

    /** Retorna a Malha Aérea em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        return "Rotas:\n\n" + rotas.toString() + "\nVôos:\n\n" + voos.toString();
    }
}