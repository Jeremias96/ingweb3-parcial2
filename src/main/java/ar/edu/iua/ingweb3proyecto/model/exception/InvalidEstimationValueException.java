package ar.edu.iua.ingweb3proyecto.model.exception;

public class InvalidEstimationValueException extends Exception{

    private static final long serialVersionUID = -6502394320474688951L;

    public InvalidEstimationValueException() {
        super();
    }

    public InvalidEstimationValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidEstimationValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEstimationValueException(String message) {
        super(message);
    }

    public InvalidEstimationValueException(Throwable cause) {
        super(cause);
    }

}
