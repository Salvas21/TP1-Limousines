/**
 * File : Driver.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas
 * Description : Classe représentant un Chauffeur
 */

package models;

public class Driver {

    private final String lastName;
    private final String firstName;
    private final String yearEmployment;
    private final String address;

    public Driver(String lastName, String firstName, String yearEmployment, String address) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.yearEmployment = yearEmployment;
        this.address = address;
    }

}
