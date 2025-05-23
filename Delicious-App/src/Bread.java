import java.util.HashMap;
import java.util.Map;

public class Bread extends Topping {
    Bread(String name, double price){
        super(name,price);
    }
    @Override
    void setPrice(int size) {
        this.price = Prices.breadPrices[size];
    }
}
