/**
 * File : Company.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : C'est la classe qui contient toute la logique de la compagnie AutodeLuxe
 */

package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Company {

    private final String driversFilePath;
    private final String limosFilePath;
    private final String ridesFilePath;

    private String[] driversLines;
    private String[] limosLines;
    private String[] ridesLines;

    private Driver[] drivers;
    private Limousine[] limousines;
    private Trip[] trips;

    public Company(String[] filePaths) {
        driversFilePath = filePaths[0];
        limosFilePath = filePaths[1];
        ridesFilePath = filePaths[2];
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
        Path r = Paths.get(ridesFilePath);
        return Files.exists(d) && Files.exists(l) && Files.exists(r);
    }

    private void readFiles() {
        driversLines = readFileLines(driversFilePath);
        limosLines = readFileLines(limosFilePath);
        ridesLines = readFileLines(ridesFilePath);
    }

    private void createModels() {
        createDrivers();
        createLimousines();
        createRides();
    }

    private void createDrivers() {
        drivers = new Driver[driversLines.length];
        for (int i = 0; i < driversLines.length; i++) {
            String[] vars = driversLines[i].split("\t");
            drivers[i] = new Driver(vars[0], vars[1], vars[2], vars[3]);
        }
    }

    private void createLimousines() {

    }

    private void createRides() {

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
                lines[i] = line;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private int getNbLines(String name) {
        Path path = Paths.get(name);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int)lines;
    }
}
