public class Drink implements IMenuItem {
    //1.Size
    //2.Flavor
    //3.Price
    String name;
    double price;
    Drink(String name, double price){
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString(){
        return String.format("%s|%.2f",name,price);
    }
}
