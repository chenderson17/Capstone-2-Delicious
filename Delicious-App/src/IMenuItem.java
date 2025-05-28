public  class IMenuItem {
String name;
double price;
IMenuItem(String name, double price){
    this.name = name;
    this.price = price;
}
@Override
    public String toString(){
    return String.format("%s|$%.2f",name,price);
}
}
