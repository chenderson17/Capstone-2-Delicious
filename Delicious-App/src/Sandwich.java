import java.util.ArrayList;

public class Sandwich implements IMenuItem {
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
double total;
boolean isToasted;
Sandwich(int size,Bread bread, ArrayList<Topping> meatToppings,ArrayList<Topping> cheeseToppings, ArrayList<Topping> regularToppings, ArrayList<Topping> sides,ArrayList<Topping> sauces, double total ,boolean isToasted){
    this.size = size;
    this.bread = bread;
    this.meatToppings = meatToppings;
    this.cheeseToppings = cheeseToppings;
    this.regularToppings = regularToppings;
    this.sauces = sauces;
    this.total = total;
    this.isToasted = isToasted;
}



    public double getTotal(){
    return total;
    }
    @Override
    public String toString(){
    return "Sandwich: " + bread + "\n" + meatToppings + "\n" + cheeseToppings+ "\n"+ regularToppings + "\n" + sauces;
    }

}
