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
        hash = 53 * hash + this.horas;
        hash = 53 * hash + this.minutos;
        return hash;
    }
}