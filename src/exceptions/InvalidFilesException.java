/**
 * File : InvalidFilesException.java
 * Created : Vendredi 11 février
 * Contributors : Mickael Salvas
 * Description : Classe représentant une Exception à propos des fichiers en arguments
 */

package exceptions;

public class InvalidFilesException extends Exception {
    public InvalidFilesException(String message) {
        super("Error --: " + message);
    }
}
