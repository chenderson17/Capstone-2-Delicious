import java.util.ArrayList;
import java.util.List;

public class Order {
    protected List<MenuItem> cart = new ArrayList<>();
    double runningTotal = 0.00;
    Order(){
    }
    public void addToCart (MenuItem item){
        cart.add(item);
        runningTotal+= item.price;
    }
    public void viewCart(){
       for(MenuItem item : cart) {
           System.out.println(item);
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
