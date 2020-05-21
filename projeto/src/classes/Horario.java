package classes;

public class Horario {
    private int horas;
    private int minutos;

    public Horario(int horas, int minutos) {
        this.horas = horas;
        this.minutos = minutos;
    }

    public int pegaHoras() {
        return horas;
    }

    public int pegaMinutos() {
        return minutos;
    }
}