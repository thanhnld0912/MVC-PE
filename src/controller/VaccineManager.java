package controller;

import model.Vaccine;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VaccineManager extends Vaccine {
    private ArrayList<Vaccine> vaccine = new ArrayList<>();
//    public void readVaccine() throws ParseException {
//        String path = "vaccines_input.txt";
//        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(",");
//                    Vaccine v = new Vaccine(data[0], data[1], Integer.parseInt(data[2]),
//                            parseDate(data[3]),Double.parseDouble(data[4]),parseDate(data[5]));
//                    vaccine.add(v);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(dateStr);
    }
//    public void LoadList() throws IOException, ParseException {
//        File filename = new File("src/vaccines_input.txt");
//        BufferedReader br = new BufferedReader(new FileReader(filename));
//        String line;
//        line = br.readLine();
//        while ((line = br.readLine()) != null) {
//            String[] input = new String[6];
//            String[] information = line.split(",");
//            int i = 0;
//            for (String info : information) {
//                String[] token = info.split("=");
//                input[i] = token[1];
//                i++;
//            }
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date productionDate = dateFormat.parse(input[3]);
//            Date expirationDate = dateFormat.parse(input[5]);
//
//            vaccine.add(new Vaccine(input[1], input[0], Integer.parseInt(input[2]), productionDate,
//                    Double.parseDouble(input[4]), expirationDate));
//        }
//        br.close();
//    }
    public void display() {
        for (Vaccine vaccine : vaccine) {
            System.out.println(vaccine.toString());
        }
    }
    public void addVaccine() throws ParseException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Input code :");
        String code = sc.nextLine().trim();
        System.out.println("Input name :");
        String name = sc.nextLine().trim();
        System.out.println("Input quantity :");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Input expiration date :");
        String exd = sc.nextLine();
        Date Doe = parseDate(exd);
        System.out.println("Input price :");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Input last Injected date :");
        String lid = sc.nextLine();
        Date Dil = parseDate(lid);
        Vaccine v = new Vaccine(code, name, quantity, Doe, price, Dil);
        checkExistVaccine(v);
    }
    public void checkExistVaccine(Vaccine v) {
        for (Vaccine vaccine1 : vaccine) {
            if (v.getVaccineCode().equals(vaccine1.getVaccineCode())) {
                System.out.println("Already have vaccine");
                break;
            }
        }
        vaccine.add(v);
    }
    public void SortName() {
        for (Vaccine vaccine1: vaccine) {

        }
    }
//     for (int i = 0; i < inputEmployees.fullemployees.size(); i++) {
//        if (inputEmployees.fullemployees.get(i).getFirstName().equals(nameToRemove) ||
//                inputEmployees.fullemployees.get(i).getLastName().equals(nameToRemove)) {
//            removedFullTiEmployee.add(inputEmployees.fullemployees.get(i));
//        }
//    }
//            if (!removedFullTiEmployee.isEmpty()) {
//        inputEmployees.fullemployees.removeAll(removedFullTiEmployee);
//        System.out.println("Deleted all employee(s) with name: " + nameToRemove);
//    } else {
//        System.out.println("Not found employee(s) with name: " + nameToRemove);
//    }
    public void LoadList() throws IOException, ParseException {
        File file = new File("src/vaccines_input.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner sc = new Scanner(fileInputStream);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        while (sc.hasNextLine()) {
            String[] listVaccine = sc.nextLine().split(",");
            if (listVaccine.length < 6) {
                System.out.println("Invalid data: " + String.join(",", listVaccine));
                continue; // Skip invalid data
            }
            try {
                String name = listVaccine[0];
                String code = listVaccine[1];
                int quantity = Integer.parseInt(listVaccine[2]);
                Date expirationDate = new Date(dateFormat.parse(listVaccine[3]).getTime());
                double price = Double.parseDouble(listVaccine[4]);
                Date lastInjectedDate = new Date(dateFormat.parse(listVaccine[5]).getTime());
                Vaccine newVaccine = new Vaccine(name, code, quantity, expirationDate, price, lastInjectedDate);
                vaccine.add(newVaccine);
            } catch (NumberFormatException | ParseException e){
                System.out.println(String.join(",", listVaccine));
            }
        }

        fileInputStream.close();

        for (Vaccine vaccine : vaccine) {
            System.out.println(vaccine);
        }
    }
    public void writeVaccine() throws IOException, ParseException {
        File outputFile = new File("vaccines_output.txt");
        FileWriter fileWriter = new FileWriter(outputFile);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Vaccine vaccine : vaccine) {
            String outputLine = "code=" + vaccine.getVaccineCode() + ", name=" + vaccine.getName() +
                    ", quantity=" + vaccine.getVaccineQuantity() + ", expirationDate=" +
                    dateFormat.format(vaccine.getExpirationDate()) + ", price=" +
                    vaccine.getVaccineCode() + ", lastInjectedDate=" +
                    dateFormat.format(vaccine.getLastInjectionDate());
            fileWriter.write(outputLine);
            fileWriter.write("\n");
        }

        fileWriter.close();
        System.out.println("Vaccines saved to vaccines_output.txt.");;
    }
    public void deleteVaccine(String code) {
        boolean removed = vaccine.removeIf(vaccine -> vaccine.getVaccineCode().equals(code));
        if (removed) {
            System.out.println("Vaccine with code " + code + " deleted.");
        } else {
            System.out.println("Vaccine with code " + code + " not found.");
        }
    }
    public void sortByName() {
        Collections.sort(vaccine, Comparator.comparing(Vaccine::getName));
        System.out.println("Vaccines sorted by name.");
    }
}
