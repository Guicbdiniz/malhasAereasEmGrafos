package classes;

import java.util.ArrayList;
import java.util.List;

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
        if (horas < horario.horas) {
            return true;
        }
        if (horas > horario.horas) {
            return false;
        }
        return minutos < horario.minutos;
    }

    /** Pega horário mais cedo de uma lista de horários */
    public static Horario pegaHorarioMaisCedo(List<Horario> listadeHorarios) {
        Horario maisCedo = listadeHorarios.get(0);

        for (int i = 1; i < listadeHorarios.size(); i++) {
            Horario atual = listadeHorarios.get(i);
            if (atual.eAntesDe(maisCedo)) {
                maisCedo = atual;
            }
        }
        return maisCedo;
    }

    /** Pega horário mais cedo de uma lista que seja depois de horário limite */
    public static Horario pegaHorarioMaisCedoDepoisDeLimite(List<Horario> listaDeHorarios, Horario limite) {
        List<Horario> horariosPosLimite = pegaSubListaDeHorariosPosLimite(listaDeHorarios, limite);

        if (horariosPosLimite.size() == 0) {
            return null;
        } else {
            return pegaHorarioMaisCedo(horariosPosLimite);
        }
    }

    /** Pega horários dentro de uma lista que acontecem depois de horário limite */
    public static List<Horario> pegaSubListaDeHorariosPosLimite(List<Horario> listaDeHorarios, Horario limite) {
        List<Horario> subLista = new ArrayList<Horario>();

        for (Horario horario : listaDeHorarios) {
            if (limite.eAntesDe(horario)) {
                subLista.add(horario);
            }
        }
        return subLista;
    }

    /**
     * Checa se o horário, ao ser somado com uma duração, ultrapassa o limite diário
     * de 24H
     */
    public boolean horarioSomadoComDuracaoEstoraODia(DuracaoDeVoo duracao) {
        int minutosTotais = this.minutos + duracao.pegaMinutos();
        int horasDosMinutos = minutosTotais / 60;
        int horasTotais = this.horas + duracao.pegaHoras() + horasDosMinutos;

        if (horasTotais >= 24) {
            return true;
        } else {
            return false;
        }

    }

    /** Pega novo horário que é a soma desse com uma duração de vôo. */
    public Horario pegaHorarioSomadoComDuracao(DuracaoDeVoo duracao) {
        int minutosTotais = this.minutos + duracao.pegaMinutos();
        int horasDosMinutos = minutosTotais / 60;
        int sobraDeMinutos = minutosTotais % 60;

        int horasTotais = this.horas + duracao.pegaHoras() + horasDosMinutos;
        int horasReais = horasTotais % 24;

        return new Horario(horasReais, sobraDeMinutos);
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

    /** Retorna o horário em forma de String que pode ser lido por usuários. */
    @Override
    public String toString() {
        return horas + ":" + minutos;
    }
}