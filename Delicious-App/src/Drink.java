public class Drink extends MenuItem {
    //1.Size
    //2.Flavor
    //3.Price
    Drink(String name, double price){
        super(name,price);
    }
    @Override
    public String toString(){
        return String.format("%s|%.2f",name,price);
    }
}
