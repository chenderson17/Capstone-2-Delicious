public class RegularTopping extends Topping{
    RegularTopping(String name, double price) {
        super(name, price);
    }

    @Override
    void setPrice(int size) {
        price = 0.00;
    }
}
