package controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Vaccine;
import java.util.Date;

public class VaccineController {
    private List<Vaccine> vaccines;

    public VaccineController() {
        vaccines = new ArrayList<>();
    }

    public void loadVaccinesFromFile(File fileName) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int lineCount = 1;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String code = getValue(parts[0].trim());
            String name = getValue(parts[1].trim());

            int quantity;
            try {
                quantity = Integer.parseInt(getValue(parts[2].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity at line " + lineCount + ": " + line);
                lineCount++;
                continue;
            }

            String expirationDateStr = getValue(parts[3].trim());

            double price;
            try {
                price = Double.parseDouble(getValue(parts[4].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price at line " + lineCount + ": " + line);
                lineCount++;
                continue;
            }

            String lastInjectedDateStr = getValue(parts[5].trim());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            Date expirationDate;
            try {
                expirationDate = dateFormat.parse(expirationDateStr);
            } catch (ParseException e) {
                System.out.println("Invalid expiration date at line " + lineCount + ": " + line);
                lineCount++;
                continue;
            }

            Date lastInjectedDate;
            try {
                lastInjectedDate = dateFormat.parse(lastInjectedDateStr);
            } catch (ParseException e) {
                System.out.println("Invalid last injected date at line " + lineCount + ": " + line);
                lineCount++;
                continue;
            }

            Vaccine vaccine = new Vaccine(code, name, quantity, expirationDate, price, lastInjectedDate);
            vaccines.add(vaccine);
            lineCount++;
        }

        reader.close();
    }

    public void displayVaccines() {
        if (vaccines.isEmpty()) {
            System.out.println("No vaccines available.");
            return;
        }

        for (Vaccine vaccine : vaccines) {
            System.out.println(vaccine);
        }
    }

    public void addNewVaccine(String code, String name, int quantity, String expirationDateStr, double price,
                              String lastInjectedDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Validate input
        if (code.isEmpty() || name.isEmpty() || expirationDateStr.isEmpty() || lastInjectedDateStr.isEmpty()) {
            System.out.println("Invalid input. Please provide all the required information.");
            return;
        }

        try {
            // Check for duplicate code
            if (isDuplicateCode(code)) {
                System.out.println("Vaccine with the same code already exists. Please provide a different code.");
                return;
            }

            Date expirationDate = dateFormat.parse(expirationDateStr);
            Date lastInjectedDate = dateFormat.parse(lastInjectedDateStr);

            Vaccine vaccine = new Vaccine(code, name, quantity, expirationDate, price, lastInjectedDate);
            vaccines.add(vaccine);
            System.out.println("Vaccine added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Vaccine not added.");
        }
    }

    public void deleteVaccine(String code) {
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getVaccineCode().contains(code)) {
                vaccines.remove(vaccine);
                System.out.println("Vaccine deleted successfully.");
                return;
            }
        }
        System.out.println("Vaccine not found.");
    }

    public void sortVaccinesByName() {
        Collections.sort(vaccines, Comparator.comparing(Vaccine::getName));
        System.out.println("Vaccines sorted by name.");
    }

    public void saveVaccinesToFile(File fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (Vaccine vaccine : vaccines) {
            String line = vaccine.toString();
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    private String getValue(String part) {
        int index = part.indexOf("=");
        return part.substring(index + 1);
    }
    private boolean isDuplicateCode(String code) {
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getVaccineCode().contains(code)) {
                return true;
            }
        }
        return false;
    }
}