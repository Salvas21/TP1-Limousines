/**
 * File : Company.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : C'est la classe qui contient toute la logique de la compagnie AutodeLuxe
 */

package models;

import models.arrays.DriverArray;
import models.arrays.LimoArray;
import models.arrays.TripArray;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Company {

    private final String driversFilePath;
    private final String limosFilePath;
    private final String tripFilePath;

    private String[] driversLines;
    private String[] limosLines;
    private String[] tripLines;

    private DriverArray drivers;
    private LimoArray limos;
    private TripArray trips;

    public Company(String[] filePaths) {
        driversFilePath = filePaths[0];
        limosFilePath = filePaths[1];
        tripFilePath = filePaths[2];
    }

    public void start() {
        if (validateFilePaths()) {
            readFiles();
            createModels();
            //menu
        }
    }

    private boolean validateFilePaths() {
        Path d = Paths.get(driversFilePath);
        Path l = Paths.get(limosFilePath);
        Path r = Paths.get(tripFilePath);
        return Files.exists(d) && Files.exists(l) && Files.exists(r);
    }

    private void readFiles() {
        driversLines = readFileLines(driversFilePath);
        limosLines = readFileLines(limosFilePath);
        tripLines = readFileLines(tripFilePath);
    }

    private void createModels() {
        createDrivers();
        createLimousines();
        createRides();
    }

    private void createDrivers() {
        drivers = new DriverArray(driversLines.length);
        for (int i = 0; i < driversLines.length; i++) {
            String[] vars = driversLines[i].split("\t");
            drivers.append(new Driver(vars[0], vars[1], vars[2], vars[3]));
        }
    }

    private void createLimousines() {
        limos = new LimoArray(limosLines.length);
    }

    private void createRides() {
        trips = new TripArray(tripLines.length);
    }

    private String[] readFileLines(String filePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            //TODO : do something about exceptions
            e.printStackTrace();
        }

        assert reader != null;
        BufferedReader buffRead = new BufferedReader(reader);
        String[] lines = new String[getNbLines(filePath)];
        int i = 0;
        String line;

        try {
            while ((line = buffRead.readLine()) != null) {
                if (!line.equals("")) {
                    lines[i] = line;
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private int getNbLines(String name) {
        long lines = 0;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            while ((line = reader.readLine()) != null)
                if (!line.equals("")) {
                    lines++;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int)lines;
    }
}
