package models.arrays;

import models.Driver;

public class DriverArray {

    private final Driver[] drivers;
    private int nbElements;

    public DriverArray(int length) {
        drivers = new Driver[length];
        nbElements = 0;
    }

    private int intParsing(String elementToParse) {
        try {
            return Integer.parseInt(elementToParse);
        } catch (NumberFormatException e) {
            //TODO : do something about exceptions
            e.printStackTrace();
            return 0;
        }
    }

    public void append(Driver driver) {
        drivers[nbElements++] = driver;
    }

    public void sort() {
        int size = drivers.length;

        for (int gap = size/2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                Driver tempDriver = drivers[i];
                int j;
                for (j = i; j >= gap && intParsing(drivers[j - gap].getYearEmployment()) > intParsing(tempDriver.getYearEmployment()); j -= gap ) {
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

    public void display() {
        for (Driver driver: drivers) {
            System.out.println("Prénom:"+driver.getFirstName());
            System.out.println("Année d'embauche:"+driver.getYearEmployment());
            System.out.println();
        }
        System.out.println("===========");
    }
}
