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

    /** Checa se horário é antes de horário comparado */
    public boolean eAntesDe(Horario horario) {
        return horas < horario.horas ? true : (minutos < horario.minutos);
    }

    /**
     * Sobrecarga no método de comparação para que testes possam ser feitos.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Horario.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Horario outro = (Horario) obj;
        if (outro.pegaHoras() != this.horas) {
            return false;
        }

        if (outro.pegaMinutos() != this.minutos) {
            return false;
        }

        return true;
    }

    /**
     * Sobrecarga no método de criação de código hash para que testes possam ser
     * feitos.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = (53 * hash + this.horas) + (53 * hash + this.minutos);
        return hash;
    }
}