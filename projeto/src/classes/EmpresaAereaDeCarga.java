package classes;

/**
 * Empresa aérea de carga com uma frota de aeronaves que só podem fazer voos de
 * ida e volta entre dois aeroportos cada.
 * 
 * Classe usada apenas para guardar dados e exibí-los de maneira plausível.
 */
public class EmpresaAereaDeCarga {

    private int numeroDeAeronaves;
    private Grafo rotasUtilizadas;

    /** Simples construtor */
    public EmpresaAereaDeCarga(int numeroDeAeronaves, Grafo rotasUtilizadas) {
        this.numeroDeAeronaves = numeroDeAeronaves;
        this.rotasUtilizadas = rotasUtilizadas;
    }

    /** Pega número de aeronaves da empresa */
    public int pegaNumeroDeAeronaves() {
        return numeroDeAeronaves;
    }

    /** Pega rotas da empresa. */
    public Grafo pegaRotasUtilizadas() {
        return rotasUtilizadas;
    }

    /** Pega dados da empresa aérea em forma de texto legível. */
    @Override
    public String toString() {
        return "Empresa Aérea de Carga\nNúmero de aeronaves: " + numeroDeAeronaves + "\n" + rotasUtilizadas.toString();
    }

}