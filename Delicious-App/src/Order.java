import java.util.ArrayList;
import java.util.List;

public class Order {
    protected List<Object> cart = new ArrayList<>();
    double runningTotal = 0.00;
    Order(){
    }
    public void addToCart (MenuItem item){
        cart.add(item);
        runningTotal+= item.price;
    }
    public void viewCart(){
       for(Object object : cart){
           if(object instanceof ArrayList<?>){
               for(int index = 0; index < ((ArrayList<?>) object).size(); index++){
                   System.out.println(((ArrayList<?>) object).get(index).toString().replace("[","").replace("]","").join(" "));
               }
           }
           System.out.println(object);
       }
    }
    public void emptyCart(){
        cart.clear();
        runningTotal = 0.0;
    }
    public double getRunningTotal(){
        return runningTotal;
    }
    public void addToTotal(Double price){
        runningTotal+= price;
    }
}
