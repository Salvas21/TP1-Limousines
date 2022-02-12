/**
 * File : Driver.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : Classe représentant un Chauffeur
 */

package models;

public class Driver {

    private final String lastName;
    private final String firstName;
    private final String yearEmployment;
    private final String address;
    private final String driverId;

    public Driver(String lastName, String firstName, String yearEmployment, String address) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.yearEmployment = yearEmployment;
        this.address = address;
//        this.driverId = "bob";
        this.driverId = generateDriverId();
    }

    private String generateDriverId() {
        StringBuilder id = new StringBuilder();
        id.append(lastName.substring(0, 3));
        id.append(firstName.charAt(0));
        id.append(yearEmployment.substring(yearEmployment.length() - 2));
        return id.toString();
    }

    public String getDriverId() {
        return driverId;
    }

    public String getYearEmployment() {
        return yearEmployment;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", yearEmployment='" + yearEmployment + '\'' +
                ", address='" + address + '\'' +
                ", driverId='" + driverId + '\'' +
                '}';
    }
}
