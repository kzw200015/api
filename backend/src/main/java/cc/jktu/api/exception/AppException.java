package cc.jktu.api.exception;

public abstract class AppException extends RuntimeException {

    public AppException(final String message) {
        super(message);
    }

    public AppException() {
    }

}
