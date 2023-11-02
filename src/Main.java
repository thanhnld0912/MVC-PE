import view.VaccineView;

import java.io.IOException;
import java.text.ParseException;
public class Main {
    public static void main(String[] args) throws IOException,ParseException{
        VaccineView view = new VaccineView();
        view.handleInput();
    }
}
