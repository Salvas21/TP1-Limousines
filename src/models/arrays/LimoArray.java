/**
 * File : LimoArray.java
 * Created : Mardi 8 février
 * Contributors : Xavier Loiselle, Pierik Landry
 * Description : Classe représentant un tableau de Limousines ainsi que la logique s'y rattachant
 */

package models.arrays;

import models.Limousine;

public class LimoArray {

    private final Limousine[] limousines;
    private int nbElements;

    public LimoArray(int length) {
        limousines = new Limousine[length];
        nbElements = 0;
    }

    public void append(Limousine limousine) {
        limousines[nbElements++] = limousine;
    }


    public int length() {
        return limousines.length;
    }

    public void sort() {
        int size = limousines.length;

        for (int gap = size/2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                Limousine tempLimousine = limousines[i];
                int j;

                for (j = i; j >= gap && limousines[j - gap].getPlate().compareTo(tempLimousine.getPlate()) > 0; j -= gap ) {
                    limousines[j] = limousines[j -gap];
                }
                limousines[j] = tempLimousine;
            }
        }
    }

    public Limousine getAt(int index) {
        return limousines[index];
    }

    public int find(String plateNbr) {
        for (int index=0; index< limousines.length; index++) {
            if (limousines[index].getPlate().equalsIgnoreCase(plateNbr)) {
                return index;
            }
        }
        return -1;
    }
}
