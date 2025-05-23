public class Cheese extends Topping{

    Cheese(String name,double price) {
        super(name,price);
    }

    @Override
    void setPrice(int size) {
        price = Prices.cheesePrices[size];
    }
}
