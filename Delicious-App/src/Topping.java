import java.util.HashMap;
import java.util.Map;

public abstract class Topping extends MenuItem{
   Topping(String name, double price){
      super(name,price);
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

}
