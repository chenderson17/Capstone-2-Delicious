import java.util.ArrayList;

public class Sandwich extends MenuItem {
    /**
     * Size
     * Bread
     * Premium Toppings
     * Regular toppings
     * Sauce
     * *Total
     */
int size;
Bread bread;
ArrayList<Topping> meatToppings = new ArrayList<>();
ArrayList<Topping> cheeseToppings = new ArrayList<>();
ArrayList<Topping> regularToppings = new ArrayList<>();
ArrayList<Topping> sauces = new ArrayList<>();
ArrayList<Topping> sides = new ArrayList<>();
boolean isToasted = false;
    Sandwich(int size){
        super("Sandwich",0.0);
        this.size = size;
    }
    @Override
    public String toString(){
    return "Sandwich:\n " + bread + "\n" + meatToppings + "\n" + cheeseToppings+ "\n"+ regularToppings + "\n" + sauces + "\n" + sides;
    }

}
