/**
 * File : Trip.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : Classe représentant un trajet
 */

package models;

public class Trip {

    private final String id;
    private final String departureCity;
    private final String arrivalCity;
    private final int startKm;
    private final int endKm;
    private final String plate;

    public Trip(String id, String departureCity, String arrivalCity, int startKm, int endKm, String plate) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.startKm = startKm;
        this.endKm = endKm;
        this.plate = plate;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", startKm=" + startKm +
                ", endKm=" + endKm +
                ", plate='" + plate + '\'' +
                '}';
    }
}
