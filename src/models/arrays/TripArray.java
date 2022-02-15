/**
 * File : TripArray.java
 * Created : Mardi 8 février
 * Contributors : Xavier Loiselle, Pierik Landry
 * Description : Classe représentant un tableau de Trajets ainsi que la logique s'y rattachant
 */

package models.arrays;

import models.Trip;

public class TripArray {

    private final Trip[] trips;
    private int nbElements;

    public TripArray(int length) {
        trips = new Trip[length];
    }

    public void append(Trip trip) {
        trips[nbElements++] = trip;
    }

    public void sortByDriverId() {
        int size = trips.length;

        for (int gap = size/2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                Trip tempTrip = trips[i];
                int j;

                for (j = i; j >= gap && trips[j - gap].getId().compareTo(tempTrip.getId()) > 0; j -= gap ) {
                    trips[j] = trips[j -gap];
                }
                trips[j] = tempTrip;
            }
        }
    }

    public void sortByPlateNbr() {
        int size = trips.length;

        for (int gap = size/2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                Trip tempTrip = trips[i];
                int j;

                for (j = i; j >= gap && trips[j - gap].getPlate().compareTo(tempTrip.getPlate()) > 0; j -= gap ) {
                    trips[j] = trips[j -gap];
                }
                trips[j] = tempTrip;
            }
        }
    }

    public Trip getAt(int index) {
        return trips[index];
    }

    public int getLength() {
        return trips.length;
    }

    public int findByLimo(String plateNbr) {
        for (int index=0; index< trips.length; index++) {
            if (trips[index].getPlate().equalsIgnoreCase(plateNbr)) {
                return index;
            }
        }
        return -1;
    }

    public int findByDriver(String driverId) {
        for (int index=0; index< trips.length; index++) {
            if (trips[index].getId().equalsIgnoreCase(driverId)) {
                return index;
            }
        }
        return -1;
    }
}
