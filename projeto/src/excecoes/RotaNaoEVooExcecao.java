package excecoes;

/**
 * Excessão para ser lançada quando um atributo de um vôo (horário) for acessada
 * em uma rota.
 * 
 * Rotas não têm horário.
 */
public class RotaNaoEVooExcecao extends Exception {

    /** Simples construtor. */
    public RotaNaoEVooExcecao(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;

}