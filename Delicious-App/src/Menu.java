import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu <E> {
    int size;
    protected ArrayList<Topping> breadList = new ArrayList<>();
    protected ArrayList<Topping> meatList = new ArrayList<>();
    protected ArrayList<Topping> cheeseList = new ArrayList<>();
    protected ArrayList<Topping> regularToppingList = new ArrayList<>();
    protected ArrayList<Topping> sauces = new ArrayList<>();
    protected ArrayList<Topping> sides = new ArrayList<>();
    GenerateDynamicMenu generate = new GenerateDynamicMenu();
    ArrayList<IMenuItem> drinks = new ArrayList<>();
    ArrayList<ArrayList<Topping>> m = new ArrayList<>();
    Menu(int size) throws FileNotFoundException {
        this.size = size;
        generate.generate(size,breadList,meatList,cheeseList, regularToppingList,sauces, sides,drinks);
        m.add(breadList);
        m.add(meatList);
        m.add(cheeseList);
        m.add(regularToppingList);
        m.add(sauces);
        m.add(sides);
    }
    //getter methods
    //helper method display
    public void display(ArrayList<Topping> list){
        for(int index = 0; index < list.size(); index++){
            System.out.println((index+1)+"."+list.get(index));
        }
    }
    public ArrayList<Topping> getBreadList(){
        return breadList;
    }
}
