/**
 * File : Company.java
 * Created : Dimanche 6 février
 * Contributors : Mickael Salvas, Xavier Loiselle
 * Description : C'est la classe qui contient toute la logique de la compagnie AutodeLuxe
 */

package models;

import exceptions.InvalidFilePathException;
import exceptions.InvalidFilesException;
import exceptions.InvalidFormatException;
import models.arrays.DriverArray;
import models.arrays.LimoArray;
import models.arrays.TripArray;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import utils.IntegerParser;

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

    public Company(String[] filePaths) throws InvalidFilesException, InvalidFilePathException {
        if (filePaths.length != 3) {
            throw new InvalidFilesException("Le nombre de fichiers requis est de 3, nombre fourni : " + filePaths.length + ".");
        }

        driversFilePath = filePaths[0];
        limosFilePath = filePaths[1];
        tripFilePath = filePaths[2];

        if (!validateFilePaths()) {
            throw new InvalidFilePathException("Un ou plusieurs chemins de fichier ne sont pas valides.");
        }
    }

    public void start() throws IOException {
        readFiles();
        createModels();
        sortModels();
        //menu
        mainMenu();
    }

    private void mainMenu() {
        int option = 0;
        do {
            printMenu();
            option = readUserInput();
            if (option < 1 || option > 3) {
                System.out.println("Option invalide.");
            }
            executeOption(option);
        } while (option != 3);
    }

    private void executeOption(int option) {
        if (option == 1) {
            showDriversLimos(askDriverId());
        }

        if (option == 2) {
            showTripsAndLimosInfo();
        }
    }

    private String askDriverId() {
        System.out.print("Veuillez entrer le numéro du conducteur: ");
        return new Scanner(System.in).nextLine();
    }

    private void showDriversLimos(String driverId) {
        boolean hasFound = false;
        for(int i = 0; i < trips.getLength(); i++) {
            Trip tempTrip = trips.getAt(i);
            if (tempTrip.getId().equalsIgnoreCase(driverId)) {
                hasFound = true;
                System.out.println(limos.getAt(limos.find(tempTrip.getPlate())));
            }
        }
        if (!hasFound) {
            System.out.println("Il n'y a pas de limousines conduite par : " + driverId);
        }
    }

    private void showTripsAndLimosInfo() {
        System.out.println("\nInformations sur les trajets et les limousines : \n");
        for (int i = 0; i < trips.getLength(); i++) {
            Trip trip = trips.getAt(i);
            Limousine limo = limos.getAt(limos.find(trip.getPlate()));
            System.out.println("Trajet : " + trip);
            System.out.println("    Limousine : " + limo + "\n");
        }
    }

    private int readUserInput() {
        System.out.print("Option: ");
        int option = 0;
        try {
            option = IntegerParser.parse(new Scanner(System.in).nextLine());
        } catch (exceptions.InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
        return option;
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Menu principal");
        System.out.println("1. Trouver toutes limousines conduit par un chauffeur.");
        System.out.println("2. Afficher caractéristiques des trajets.");
        System.out.println("3. Quitter.");
    }

    private boolean validateFilePaths() {
        Path d = Paths.get(driversFilePath);
        Path l = Paths.get(limosFilePath);
        Path r = Paths.get(tripFilePath);
        return Files.exists(d) && Files.exists(l) && Files.exists(r);
    }

    private void readFiles() throws IOException {
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

    private String[] readFileLines(String filePath) throws IOException {
        FileReader r = new FileReader(filePath);
        BufferedReader buffRead = new BufferedReader(r);
        String[] lines = new String[getNbLines(filePath)];
        int i = 0;
        String line;

        while ((line = buffRead.readLine()) != null) {
            if (!line.equals("")) {
                lines[i] = line;
                i++;
            }
        }
        return lines;
    }

    private int getNbLines(String name) throws IOException {
        long lines = 0;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(name));
        while ((line = reader.readLine()) != null) {
            if (!line.equals("")) {
                lines++;
            }
        }
        return (int)lines;
    }

    private void sortModels() {
        try {
            drivers.sort();
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
        limos.sort();
        trips.sortByDriverId();
    }
}
