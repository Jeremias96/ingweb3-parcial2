package ar.edu.iua.ingweb3proyecto.model.exception;

public class NullListException extends Exception{

    private static final long serialVersionUID = 3022537710258718260L;

    public NullListException() {
    }

    public NullListException(String message) {
        super(message);
    }

    public NullListException(Throwable cause) {
        super(cause);
    }

    public NullListException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
}
