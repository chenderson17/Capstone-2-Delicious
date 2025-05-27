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
ArrayList<Topping> meatToppings;
ArrayList<Topping> cheeseToppings;
ArrayList<Topping> regularToppings;
ArrayList<Topping> sauces;
double total;
Sandwich(int size,Bread bread, ArrayList<Topping> meatToppings,ArrayList<Topping> cheeseToppings, ArrayList<Topping> regularToppings, ArrayList<Topping> sauces, double total){
    this.size = size;
    this.bread = bread;
    this.meatToppings = meatToppings;
    this.cheeseToppings = cheeseToppings;
    this.regularToppings = regularToppings;
    this.sauces = sauces;
    this.total = total;
}



    public double getTotal(){
    return total;
    }
}
