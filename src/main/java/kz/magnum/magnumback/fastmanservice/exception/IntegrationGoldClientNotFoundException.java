package kz.magnum.magnumback.fastmanservice.exception;

public class IntegrationGoldClientNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4012977829320559101L;

    public IntegrationGoldClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
