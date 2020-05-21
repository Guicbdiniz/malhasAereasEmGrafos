package classes;

/**
 * Grafo dirigido.
 */
public class GrafoDirigido extends Grafo {

    @Override
    public void adicionaAresta(String idOrigem, String idDestino, PesoDaAresta peso) {
        if (this.pegaVertice(idOrigem) == null) {
            this.adicionaVertice(idOrigem);
        }

        if (this.pegaVertice(idDestino) == null) {
            this.adicionaVertice(idDestino);
        }

        this.pegaVertice(idOrigem).adicionaVizinho(this.pegaVertice(idDestino), peso);
    }
}