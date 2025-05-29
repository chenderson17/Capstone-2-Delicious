public class Meat extends Topping{
    Meat(String name,double price) {
        super(name,price);
    }

    @Override
    void setPrice(int size) {
        this.price = Prices.meatPrices[size];
    }
}
