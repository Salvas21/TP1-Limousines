/**
 * File : InvalidFilePathException.java
 * Created : Vendredi 11 février
 * Contributors : Mickael Salvas
 * Description : Classe représentant une Exception à propos des chemins de fichiers
 */

package exceptions;

public class InvalidFilePathException extends Exception {
    public InvalidFilePathException(String message) {
        super("Error --: " + message);
    }
}
