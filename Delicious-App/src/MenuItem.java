public class MenuItem {
protected String name;
protected double price;
MenuItem(String name, double price){
    this.name = name;
    this.price = price;
}
@Override
    public String toString(){
    return String.format("%s..................$%.2f",name,price);
}
//getters
    public String getName(){
      return name;
    }
    public double getPrice(){
    return price;
    }
//setters
    public void setName(String name){
    this.name = name;
    }
    public void setPrice(Double price){
    this.price = price;
    }
}
