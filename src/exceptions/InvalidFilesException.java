package exceptions;

public class InvalidFilesException extends Exception {
    public InvalidFilesException(String message) {
        super("Error --: " + message);
    }
}
