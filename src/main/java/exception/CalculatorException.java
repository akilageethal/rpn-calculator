package main.java.exception;

/**
 * Generic exception class to handle all exception scenarios and notify the user.
 * This will be thrown in each exception scenario after catching the system exception
 */
public class CalculatorException extends Exception {

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculatorException(String message) {
        super(message);
    }
}
