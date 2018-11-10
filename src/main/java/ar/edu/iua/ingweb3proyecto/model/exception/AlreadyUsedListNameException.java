package ar.edu.iua.ingweb3proyecto.model.exception;

public class AlreadyUsedListNameException extends Exception{

    private static final long serialVersionUID = 2229892222707760132L;

    public AlreadyUsedListNameException() {
        super();
    }

    public AlreadyUsedListNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AlreadyUsedListNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyUsedListNameException(String message) {
        super(message);
    }

    public AlreadyUsedListNameException(Throwable cause) {
        super(cause);
    }

}
