package ar.edu.iua.ingweb3proyecto.business.exception;

public class InvalidSortException extends Exception {

    private static final long serialVersionUID = -6708815061173372880L;

    public InvalidSortException() {
    }

    public InvalidSortException(String message) {
        super(message);
    }

    public InvalidSortException(Throwable cause) {
        super(cause);
    }

    public InvalidSortException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
