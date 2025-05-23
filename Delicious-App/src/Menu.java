import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu <E> {
    int size;
    protected ArrayList<Bread> breadList = new ArrayList<>();
    protected ArrayList<Meat> meatList = new ArrayList<>();
    protected ArrayList<Cheese> cheeseList = new ArrayList<>();
    protected ArrayList<RegularTopping> regularToppingList = new ArrayList<>();
    protected ArrayList<RegularTopping> sauces = new ArrayList<>();
    protected ArrayList<RegularTopping> sides = new ArrayList<>();
    GenerateDynamicMenu generate = new GenerateDynamicMenu();
    Menu(int size) throws FileNotFoundException {
        this.size = size;
        generate.generate(size,breadList,meatList,cheeseList, regularToppingList,sauces, sides);
    }
    //getter methods
    public ArrayList<Bread> getBreadList(){
        display(breadList);
        return breadList;
    }
    public ArrayList<Meat> getMeatList() {
        display(meatList);
        return meatList;
    }
    public ArrayList<Cheese> getCheeseList(){
        display(cheeseList);
        return cheeseList;
    }
    public ArrayList<RegularTopping> getRegularToppingList(){
        display(regularToppingList);
        return regularToppingList;
    }
    public ArrayList<RegularTopping> getSauces(){
        display(sauces);
        return sauces;
    }
    public ArrayList<RegularTopping> getSides(){
        display(sides);
        return sides;
    }
    //helper method display
    public <E> void display(List<E> list){
        for(int index = 0; index < list.size(); index++){
            System.out.println((index+1)+"."+list.get(index));
        }
    }
}
