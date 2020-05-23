package classes;

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

    /** Retorna a Malha Aérea em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        return "Rotas:\n\n" + rotas.toString() + "\nVôos:\n\n" + voos.toString();
    }
}