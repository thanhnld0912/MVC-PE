package view;

import controller.VaccineController;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
public class VaccineView {
    private VaccineController controller;
    private Scanner scanner;

    public VaccineView() {
        controller = new VaccineController();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("=== Vaccine Management System ===");
        System.out.println("1. Load vaccines from file");
        System.out.println("2. Display all vaccines");
        System.out.println("3. Add a new vaccine");
        System.out.println("4. Delete a vaccine");
        System.out.println("5. Sort vaccines by name");
        System.out.println("6. Save vaccines to file");
        System.out.println("0. Exit");
        System.out.println("=================================");
    }

    public void handleInput() {
        int choice = -1;

        while (choice != 0) {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    loadVaccinesFromFile();
                    break;
                case 2:
                    displayVaccines();
                    break;
                case 3:
                    addNewVaccine();
                    break;
                case 4:
                    deleteVaccine();
                    break;
                case 5:
                    sortVaccinesByName();
                    break;
                case 6:
                    saveVaccinesToFile();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }

    public void loadVaccinesFromFile() {

        File fileName =  new File("src/vaccines_input.txt");
        try {
            controller.loadVaccinesFromFile(fileName);
            System.out.println("Vaccines loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading vaccines from file: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Invalid data format in the file: " + e.getMessage());
        }
    }

    public void displayVaccines() {
        controller.displayVaccines();
    }

    public void addNewVaccine() {
        System.out.print("Enter the vaccine code: ");
        String code = scanner.nextLine();
        System.out.print("Enter the vaccine name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity;
        while (true) {
            try {
                quantity = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid quantity, enter again");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the expiration date (dd/MM/yyyy): ");
        String expirationDateStr = scanner.nextLine();
        System.out.print("Enter the price: ");
        double price;
        while (true) {
            try{
                price = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid price, enter again");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the last injected date (dd/MM/yyyy): ");
        String lastInjectedDateStr = scanner.nextLine();

        controller.addNewVaccine(code, name, quantity, expirationDateStr, price, lastInjectedDateStr);
    }

    public void deleteVaccine() {
        System.out.print("Enter the vaccine code to delete: ");
        String code = scanner.nextLine();

        controller.deleteVaccine(code);
    }

    public void sortVaccinesByName() {
        controller.sortVaccinesByName();
    }

    public void saveVaccinesToFile() {

        File fileName = new File("src/vaccine_output.txt");

        try {
            controller.saveVaccinesToFile(fileName);
            System.out.println("Vaccines saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving vaccines to file: " + e.getMessage());
        }
    }
}