import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HomeScreen {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ANSI_PURPLE + "DELI-" + ANSI_RESET + "LICIOUS");
        UserInterface ui = new UserInterface();
        ui.run();
    }
}
