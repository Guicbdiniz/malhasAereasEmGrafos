package classes;

/**
 * Duração de Vôo.
 * 
 * Sua única responsabilidade é guardar as horas inteiras de vôo e os minutos
 * que sobram.
 */
public class DuracaoDeVoo {
    private int horas;
    private int minutos;

    /** Simples construtor. */
    public DuracaoDeVoo(int horas, int minutos) {
        this.horas = horas;
        this.minutos = minutos;
    }

    /** Pega hora(s) inteira(s) de vôo. */
    public int pegaHoras() {
        return horas;
    }

    /** Pega minuto(s) que sobra(m) de vôo. */
    public int pegaMinutos() {
        return minutos;
    }
}