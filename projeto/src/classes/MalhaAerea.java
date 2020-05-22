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
}