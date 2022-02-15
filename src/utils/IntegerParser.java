package utils;

import exceptions.InvalidFormatException;

/**
 * File : IntegerParser.java
 * Package : utils
 * Contributors : Pierik Landry
 * Created : 2022-02-11
 * Last edit : 15:07 p.m.
 * Description : Classe ayant pour but transformer une String en Int
 */
public class IntegerParser {

    public static int parse(String elementToParse) throws InvalidFormatException {
        try {
            return Integer.parseInt(elementToParse);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Format invalide fournit : " + elementToParse + ".");
        }

    }

}
