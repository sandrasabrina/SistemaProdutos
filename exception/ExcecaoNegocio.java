package exception;

/**
 * Exceção personalizada lançada ao violar regras de negócio no sistema.
 */
public class ExcecaoNegocio extends Exception {
    //Definition: The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object. If no match is found, then an InvalidClassException is thrown.
    private static final long serialVersionUID = 1L; 

    /**
     * Cria uma exceção representando erro de negócio.
     * @param mensagem mensagem detalhada explicando o motivo do erro
     */
    public ExcecaoNegocio(String mensagem) {
        super(mensagem);
    }
}
