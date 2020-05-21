package classes;

/**
 * Horário de vôo.
 * 
 * Sua única responsabilidade é de guardar a hora e o minuto do horário de vôo.
 */
public class Horario {
    private int horas;
    private int minutos;

    /** Simples construtor. */
    public Horario(int horas, int minutos) {
        this.horas = horas;
        this.minutos = minutos;
    }

    /** Pega hora(s) do horário. */
    public int pegaHoras() {
        return horas;
    }

    /** Pega minuto(s) do horário. */
    public int pegaMinutos() {
        return minutos;
    }
}