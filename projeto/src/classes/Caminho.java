package classes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import excecoes.RotaNaoEVooExcecao;

/**
 * Caminho entre vértices em um grafo.
 */
public class Caminho {

    private Queue<Vertice> sequenciaDeVertices;

    /** Simples construtor */
    public Caminho(Queue<Vertice> sequenciaDeVertices) {
        this.sequenciaDeVertices = sequenciaDeVertices;
    }

    /**
     * Pega um caminho a partir de uma pilha de vertices, considerando que o
     * primeiro que foi inserido será o vértice incial do caminho (e a ordem
     * seguirá).
     * 
     * @param pilha - pilha de vértices com a ordem do caminho.
     * @return caminho gerado a partir da pilha.
     */
    public static Caminho pegaCaminhoDePilha(Stack<Vertice> pilha) {

        Stack<Vertice> pilhaInicial = new Stack<Vertice>();
        pilhaInicial.addAll(pilha);

        Stack<Vertice> pilhaExtra = new Stack<Vertice>();
        while (!pilhaInicial.isEmpty()) {
            pilhaExtra.add(pilhaInicial.pop());
        }

        Queue<Vertice> sequenciaDeVertices = new LinkedList<Vertice>();
        while (!pilhaExtra.isEmpty()) {
            sequenciaDeVertices.add(pilhaExtra.pop());
        }

        return new Caminho(sequenciaDeVertices);
    }

    /** Pega sequência de vértices do caminho. */
    public Queue<Vertice> pegaSequenciaDeVertices() {
        return this.sequenciaDeVertices;
    }

    /**
     * Pega sequencia de IDs dos vértices do caminho.
     * 
     * @return sequência de IDs dos vértices.
     */
    public Queue<String> pegaSequenciaDeIDDeVertices() {
        Queue<Vertice> filaDeVerticesAdicional = new LinkedList<Vertice>(sequenciaDeVertices);
        Queue<String> sequenciaDeIDDeVertices = new LinkedList<String>();
        while (!filaDeVerticesAdicional.isEmpty()) {
            sequenciaDeIDDeVertices.add(filaDeVerticesAdicional.remove().pegaID());
        }

        return sequenciaDeIDDeVertices;
    }

    /**
     * Pega horário mais tarde de vôo que sai do primeiro vértice do caminho e chega
     * antes do horário limite.
     * 
     * Se nenhum horário for cedo os suficiente para chegar antes do limite, o
     * método retorna NULL.
     * 
     * @param limite - limite de horário para se chegar no destino final.
     * @return horário mais tarde de saída do vértice inicial.
     */
    public Horario pegaHorarioMaisTardeDeSaida(Horario limite) throws RotaNaoEVooExcecao {
        return AnalisadorDeHorarios.analisaHorarioMaisTardeQueChegaNoDestino(sequenciaDeVertices, limite);
    }

    /** Retorna o caminho em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        StringBuilder textoDeCadaVertice = new StringBuilder("\t");

        for (Vertice vertice : this.sequenciaDeVertices) {
            textoDeCadaVertice.append(vertice.pegaID() + " ");
        }

        return "Caminho: \n" + textoDeCadaVertice.toString();
    }

}