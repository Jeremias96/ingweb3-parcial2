package ar.edu.iua.ingweb3proyecto.model.exception;

public class InvalidListNameException extends Exception{

    private static final long serialVersionUID = 2229892222707760132L;

    public InvalidListNameException() {
        super();
    }

    public InvalidListNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidListNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidListNameException(String message) {
        super(message);
    }

    public InvalidListNameException(Throwable cause) {
        super(cause);
    }

}
