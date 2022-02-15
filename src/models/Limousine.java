/**
 * File : Limousine.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : Classe représentant une limousine
 */

package models;

public class Limousine {
    
    private final String plate;
    private final double fuelCapacity;
    private final String color;

    public Limousine(String plate, double fuelCapacity, String color) {
        this.plate = plate;
        this.fuelCapacity = fuelCapacity;
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return "Limousine{" +
                "plate='" + plate + '\'' +
                ", fuelCapacity=" + fuelCapacity +
                ", color='" + color + '\'' +
                '}';
    }
}
