import java.util.ArrayList;

public class Sandwich extends IMenuItem {
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
ArrayList<Topping> meatToppings;
ArrayList<Topping> cheeseToppings;
ArrayList<Topping> regularToppings;
ArrayList<Topping> sauces;
ArrayList<Topping> sides;
boolean isToasted;
Sandwich(int size, double price, Bread bread, ArrayList<Topping> meatToppings,ArrayList<Topping> cheeseToppings, ArrayList<Topping> regularToppings, ArrayList<Topping> sides,ArrayList<Topping> sauces,boolean isToasted){
    super("Sandwich",price);
    this.size = size;
    this.bread = bread;
    this.meatToppings = meatToppings;
    this.cheeseToppings = cheeseToppings;
    this.regularToppings = regularToppings;
    this.sides = sides;
    this.sauces = sauces;
    this.isToasted = isToasted;
}

    @Override
    public String toString(){
    return "Sandwich:\n " + bread + "\n" + meatToppings + "\n" + cheeseToppings+ "\n"+ regularToppings + "\n" + sauces + "\n" + sides;
    }

}
