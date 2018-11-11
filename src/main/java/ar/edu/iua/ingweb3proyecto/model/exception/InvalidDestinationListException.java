package ar.edu.iua.ingweb3proyecto.model.exception;

public class InvalidDestinationListException extends Exception{

    private static final long serialVersionUID = -2385947025664155280L;

    public InvalidDestinationListException() {
        super();
    }

    public InvalidDestinationListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidDestinationListException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDestinationListException(String message) {
        super(message);
    }

    public InvalidDestinationListException(Throwable cause) {
        super(cause);
    }

}
