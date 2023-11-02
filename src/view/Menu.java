package view;
import controller.VaccineManager;

import java.io.IOException;
import java.text.ParseException;

public class Menu {
    public static void main(String[] args) throws ParseException, IOException {
        VaccineManager manager = new VaccineManager();
        manager.LoadList();
        manager.display();
        manager.addVaccine();
        manager.writeVaccine();
    }

}
