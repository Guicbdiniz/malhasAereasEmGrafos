package classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import excecoes.RotaNaoEVooExcecao;

/**
 * Analisador de horários dentro de um caminho.
 * 
 * Classe feita para tratar das obrigações de escolha de horário mais tarde
 * dentro de um caminho que possibilite se chegar no destino antes do horário
 * limite.
 */
public class AnalisadorDeHorarios {

    private Queue<Vertice> sequenciaDeVertices;
    private Queue<Vertice> sequenciaDeVerticesLocal;
    private Horario limiteGlobal;
    private Horario limiteLocal;
    private DuracaoDeVoo duracaoInicial;
    private DuracaoDeVoo duracaoLocal;
    private List<Horario> horariosDeSaidaPossiveis;
    private boolean horarioTrabalhadoEValido;

    /** Simples construtor privado */
    private AnalisadorDeHorarios(Queue<Vertice> sequenciaDeVertices, Horario limite) {
        this.sequenciaDeVertices = new LinkedList<Vertice>(sequenciaDeVertices);
        this.limiteGlobal = limite;
    }

    /**
     * Pega horário mais tarde de vôo que sai do primeiro vértice do caminho e chega
     * antes do horário limite.
     * 
     * Se nenhum horário for cedo os suficiente para chegar antes do limite, o
     * método retorna NULL.
     * 
     * @param limite              - limite de horário para se chegar no destino
     *                            final.
     * @param sequenciaDeVertices - sequencia de vértices do caminho.
     * @return horário mais tarde de saída do vértice inicial.
     * @throws RotaNaoEVooExcecao
     */
    public static Horario analisaHorarioMaisTardeQueChegaNoDestino(Queue<Vertice> sequenciaDeVertices, Horario limite)
            throws RotaNaoEVooExcecao {

        AnalisadorDeHorarios analisador = new AnalisadorDeHorarios(sequenciaDeVertices, limite);
        return analisador.pegaHorarioMaisTardeQueChegaNoDestino();
    }

    /**
     * Pega o horário inicial mais tarde dentro da sequência que chegue no destino
     * antes do limite.
     */
    private Horario pegaHorarioMaisTardeQueChegaNoDestino() throws RotaNaoEVooExcecao {
        Vertice primeiroVertice = sequenciaDeVertices.remove();
        Vertice segundoVertice = sequenciaDeVertices.peek();
        duracaoInicial = primeiroVertice.pegaPeso(segundoVertice).pegaDuracao();
        horariosDeSaidaPossiveis = primeiroVertice.pegaPeso(segundoVertice).pegaHorario();

        analisaTodasAsPossibilidadesDeHorarioInicial();

        if (horariosDeSaidaPossiveis.size() > 0) {
            return Horario.pegaHorarioMaisTarde(horariosDeSaidaPossiveis);
        } else {
            return null;
        }
    }

    /**
     * Analisa todos os horários iniciais, checando se, ao partir dele, é possível
     * chegar no destino antes do limite.
     * 
     * Se não for possível, ele é excluído da lista de horários possíveis.
     */
    private void analisaTodasAsPossibilidadesDeHorarioInicial() throws RotaNaoEVooExcecao {
        List<Horario> novosHorariosDeSaidaPossiveis = new ArrayList<Horario>(horariosDeSaidaPossiveis);

        for (Horario horario : horariosDeSaidaPossiveis) {
            sequenciaDeVerticesLocal = new LinkedList<Vertice>(sequenciaDeVertices);
            limiteLocal = horario.pegaHorarioSomadoComDuracao(duracaoInicial);

            if (horario.eAntesDe(limiteLocal)) { // Ou seja, não estorou o dia
                horarioTrabalhadoEValido = true;
                checaSeHorarioTrabalhadoEValido();
            } else {
                horarioTrabalhadoEValido = false;
            }

            if (!horarioTrabalhadoEValido) {
                novosHorariosDeSaidaPossiveis.remove(horario);
            }
        }

        horariosDeSaidaPossiveis = novosHorariosDeSaidaPossiveis;
    }

    /**
     * Analisa horário trabalhado, checando se é possível pegar o primeiro vôo nele
     * e chegar no destino antes do limite.
     */
    private void checaSeHorarioTrabalhadoEValido() throws RotaNaoEVooExcecao {
        while (sequenciaDeVerticesLocal.size() > 1) {
            Vertice atual = sequenciaDeVerticesLocal.remove();
            Vertice proximo = sequenciaDeVerticesLocal.peek();
            List<Horario> horariosDoVertice = atual.pegaPeso(proximo).pegaHorario();
            duracaoLocal = atual.pegaPeso(proximo).pegaDuracao();
            Horario maisCedo = Horario.pegaHorarioMaisCedoDepoisDeLimite(horariosDoVertice, limiteLocal);

            if (maisCedo == null) {
                horarioTrabalhadoEValido = false;
                return;
            } else if (limiteGlobal.eAntesDe(maisCedo.pegaHorarioSomadoComDuracao(duracaoLocal))) {
                horarioTrabalhadoEValido = false;
                return;
            } else if (maisCedo.horarioSomadoComDuracaoEstoraODia(duracaoLocal)) {
                horarioTrabalhadoEValido = false;
                return;
            } else {
                limiteLocal = maisCedo.pegaHorarioSomadoComDuracao(duracaoLocal);
            }
        }
    }

}