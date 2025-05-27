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
double total;
Sandwich(int size,Bread bread, Meat meat, ArrayList<Topping> meatToppings,ArrayList<Topping> cheeseToppings, ArrayList<Topping> regularToppings, double total){
    this.size = size;
    this.bread = bread;
    this.meatToppings = meatToppings;
    this.cheeseToppings = cheeseToppings;
    this.regularToppings = regularToppings;
    this.total = total;
}
public double calculateTotal(){

    return bread.getPrice();
}

}
