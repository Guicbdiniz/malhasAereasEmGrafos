package classes;

public class DuracaoDeVoo {
    private int horas;
    private int minutos;

    public DuracaoDeVoo(int horas, int minutos) {
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