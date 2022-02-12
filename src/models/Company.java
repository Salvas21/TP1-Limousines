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
    private String[] tripsLines;

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
            sortModels();
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
        tripsLines = readFileLines(tripFilePath);
    }

    private void createModels() {
        createDrivers();
        createLimousines();
        createTrips();
    }

    private void createDrivers() {
        drivers = new DriverArray(driversLines.length);
        for (String driversLine : driversLines) {
            String[] vars = splitString(driversLine);
            drivers.append(new Driver(vars[0], vars[1], vars[2], vars[3]));
        }
    }

    private void createLimousines() {
        limos = new LimoArray(limosLines.length);
        for (String limosLine : limosLines) {
            String[] vars = splitString(limosLine);
            limos.append(new Limousine(vars[0], Double.parseDouble(vars[1]), vars[2]));
        }
    }

    private void createTrips() {
        trips = new TripArray(tripsLines.length);
        for (String tripsLine : tripsLines) {
            String[] vars = splitString(tripsLine);
            trips.append(new Trip(vars[0], vars[1], vars[2], Integer.parseInt(vars[3]), Integer.parseInt(vars[4]), vars[5]));
        }
    }

    private String[] splitString(String s) {
        return s.split("\t");
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

    private void sortModels() {
        drivers.sort();
        limos.sort();
        trips.sort();
    }
}
