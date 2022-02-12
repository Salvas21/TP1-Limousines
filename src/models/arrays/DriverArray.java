/**
 * File : DriverArray.java
 * Created : Mardi 8 février
 * Contributors : Xavier Loiselle, Pierik Landry
 * Description : Classe représentant un tableau de Chauffeurs ainsi que la logique s'y rattachant
 */

package models.arrays;

import exceptions.InvalidFormatException;
import models.Driver;
import utils.IntegerParser;

public class DriverArray {

    private final Driver[] drivers;
    private int nbElements;

    public DriverArray(int length) {
        drivers = new Driver[length];
        nbElements = 0;
    }

    public void append(Driver driver) {
        drivers[nbElements++] = driver;
    }

    public void sort() throws InvalidFormatException {
        int size = drivers.length;

        for (int gap = size/2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                Driver tempDriver = drivers[i];
                int j;
                for (j = i; j >= gap && IntegerParser.parse(drivers[j - gap].getYearEmployment()) > IntegerParser.parse(tempDriver.getYearEmployment()); j -= gap ) {
                    drivers[j] = drivers[j -gap];
                }
                drivers[j] = tempDriver;
            }
        }
    }

    public Driver getAt(int index) {
        return drivers[index];
    }

    public int find(String driverId) {
        for (int index=0; index< drivers.length; index++) {
            if (drivers[index].getDriverId().equalsIgnoreCase(driverId)) {
                return index;
            }
        }
        return -1;
    }
}
