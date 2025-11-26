package exception;

// Exceção customizada para regras de negócio (checked exception)
public class NegocioException extends Exception {
    public NegocioException(String message) {
        super(message);
    }
}
