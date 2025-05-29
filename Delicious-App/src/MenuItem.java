public class MenuItem {
String name;
double price;
MenuItem(String name, double price){
    this.name = name;
    this.price = price;
}
@Override
    public String toString(){
    return String.format("%s|$%.2f",name,price);
}
}
