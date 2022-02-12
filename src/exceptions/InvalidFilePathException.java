package exceptions;

public class InvalidFilePathException extends Exception {
    public InvalidFilePathException(String message) {
        super("Error --: " + message);
    }
}
