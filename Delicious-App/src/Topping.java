import java.util.HashMap;
import java.util.Map;

public abstract class Topping {
   protected String name;
   protected int size; //user input
   protected double price;
   Topping(String name, double price){
      this.name = name;
      this.price = price;
   }
   //setter
   abstract void setPrice(int size);
   public void setName(String name){
      this.name = name;
   }
   //getter
   public String getName(){
      return this.name;
   }

   public double getPrice(){
      return this.price;
   }

    public String toString(){
      return String.format("%s|$%.2f", name, price);
    }

}
