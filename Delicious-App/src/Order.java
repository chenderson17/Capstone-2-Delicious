import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    protected List<Object> cart = new ArrayList<>();
    double runningTotal = 0.00;
    Order(){
    }
    //drinks
    //chips
    public void addToCart (IMenuItem item){
        cart.add(item);
        runningTotal+= item.price;
    }
    public String viewCart(){
        return cart.toString();
    }
    public void emptyCart(){
        cart.clear();
        runningTotal = 0.0;
    }
    public double getRunningTotal(){
        return runningTotal;
    }
}
