import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HomeScreen {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) throws FileNotFoundException {
        String storeName = "DELI-LICIOUS";
        String[] colors = {ANSI_RED,ANSI_GREEN,ANSI_YELLOW,ANSI_BLUE,ANSI_PURPLE,ANSI_CYAN};
        for(char letter : storeName.toCharArray()){
            System.out.print(colors[(int)(Math.random() * (colors.length))] + String.valueOf(letter) + ANSI_RESET);
        }
        System.out.println();
        UserInterface ui = new UserInterface();
        ui.run();
    }
}
