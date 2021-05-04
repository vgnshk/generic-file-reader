package designtest.striim.genericfilereader.exception;

/**
 * Custom exception to be created by the UserInput validator
 */
public class InvalidUserInputException extends Exception {
    public InvalidUserInputException(String message) {
        super(message);
    }
}
