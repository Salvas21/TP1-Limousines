package exceptions;

/**
 * File : InvalidFormatException.java
 * Package : exceptions
 * Contributors : Pierik Landry
 * Created : 2022-02-11
 * Last edit : 11:03 p.m.
 * Description : Classe représentant une Exception à propos des formats pour IntegerParsing en arguments
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException (String message) {
        super("Erreur --: "+message);
    }
}
