package exception;

/**
 * Exceção personalizada lançada ao violar regras de negócio no sistema.
 */
public class NegocioException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Cria uma exceção representando erro de negócio.
     * @param mensagem mensagem detalhada explicando o motivo do erro
     */
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
