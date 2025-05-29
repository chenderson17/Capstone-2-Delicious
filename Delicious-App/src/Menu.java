import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu <E> {
    int size;
    protected ArrayList<Topping> breadList = new ArrayList<>();
    protected ArrayList<Topping> meatList = new ArrayList<>();
    protected ArrayList<Topping> cheeseList = new ArrayList<>();
    protected ArrayList<Topping> regularToppingList = new ArrayList<>();
    protected ArrayList<Topping> sauces = new ArrayList<>();
    ArrayList<Topping> sides = new ArrayList<>();
    ArrayList<MenuItem> chips = new ArrayList<>();
    GenerateDynamicMenu generate = new GenerateDynamicMenu();
    ArrayList<MenuItem> drinks = new ArrayList<>();
    ArrayList<ArrayList<Topping>> m = new ArrayList<>();
    Menu(int size) throws FileNotFoundException {
        this.size = size;
        generate.generate(size,breadList,meatList,cheeseList, regularToppingList,sauces, sides,drinks, chips);
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
