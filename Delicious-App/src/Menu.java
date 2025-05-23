import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu <E> {
    int size;
    ArrayList<Bread> breadList = new ArrayList<>();
    ArrayList<Meat> meatList = new ArrayList<>();
    ArrayList<Cheese> cheeseList = new ArrayList<>();
    ArrayList<RegularTopping> regularToppingList = new ArrayList<>();
    ArrayList<RegularTopping> sauces = new ArrayList<>();
    ArrayList<RegularTopping> sides = new ArrayList<>();
    ArrayList<Object> m = new ArrayList<>();
    GenerateDynamicMenu generate = new GenerateDynamicMenu();
    Menu(int size) throws FileNotFoundException {
        this.size = size;
        generate.generate(size,breadList,meatList,cheeseList, regularToppingList,sauces, sides);
        m.add(breadList);m.add(meatList);m.add(cheeseList);m.add(regularToppingList);m.add(sauces);m.add(sides);
    }
}
