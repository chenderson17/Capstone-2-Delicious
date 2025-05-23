import java.util.ArrayList;

public class Sandwich {
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
ArrayList<Meat> meatToppings;
ArrayList<Cheese> cheeseToppings;
ArrayList<RegularTopping> regularToppings;
double total;
Sandwich(int size,Bread bread, Meat meat, ArrayList<Meat> meatToppings, ArrayList<RegularTopping> regularToppings, ArrayList<Cheese> cheeseToppings, double total){
    this.size = size;
    this.bread = bread;
    this.meatToppings = meatToppings;
    this.cheeseToppings = cheeseToppings;
    this.regularToppings = regularToppings;
    this.total = total;
}

}
