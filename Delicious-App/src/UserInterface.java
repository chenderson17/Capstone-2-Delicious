import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class UserInterface {
    Scanner in = new Scanner(System.in);
    String userInput;
    public double runningTotal = 0.0;
    ArrayList<IMenuItem> cart = new ArrayList<>();
    public Order order = new Order(cart);

    void run(){
        String userInput = "";
        while(!userInput.equalsIgnoreCase( "0")){
            try {
                /**ORDERING INTERFACE*/
                System.out.print("""
                        1) Press 1 to order a sandwich
                        2) View Cart
                        0) Press 0 to exit
                        User Input:""");
                userInput = in.nextLine();
                switch (userInput){
                    case "1":
                        orderASandwich();
                        break;
                    case "2":
                        System.out.println(cart + "\n$" + runningTotal);
                }
            }
            catch (Exception error){
                System.out.println("Error: " + error + "Please try again.");
            }
        }
    }
    public void orderASandwich(){

        int input = 0;
        int size = 0;
        Bread bread = null;
        ArrayList<Topping> meats = new ArrayList<>();
        ArrayList<Topping> cheeses = new ArrayList<>();
        ArrayList<Topping> regularT = new ArrayList<>();
        ArrayList<Topping> sauces = new ArrayList<>();
        String[] pages = {"s","b","m","c","r","sauce","complete"};
        Menu menu = null;
        //
        while(input < pages.length) {
            try {
                switch (pages[input]){
                    case "s":
                        //order bread
                        System.out.print("""
                        Press [1] 4" sandwich [2] 8" sandwich [3] 12" sandwich
                        Your input: """);
                        size = in.nextInt() - 1;
                        in.nextLine();
                        input++;
                        break;
                    case "b":
                        menu = new Menu(size);
                        System.out.println("BREAD\nSelect a bread type (enter the number next to the bread):");
                        menu.display((ArrayList<Topping>) menu.m.get(0));
                        System.out.print("Your Input: ");
                        bread = (Bread) menu.getBreadList().get(in.nextInt() -1);
                        in.nextLine();
                        runningTotal+= bread.getPrice();
                        input++;
                        break;
                    case "m":
                        if(!addPremiumToppings(meats,menu, 1,in,"Meat",size,Prices.extraMeat)){
                            System.out.println(meats);
                            input++;
                        };
                        break;
                    case "c":
                        if(!addPremiumToppings(cheeses, menu, 2, in, "Cheese", size, Prices.extraCheese)) {
                            System.out.println(cheeses);
                            input++;
                        }
                        break;
                    case "r":
                        if(!addRegularToppings(menu, regularT,3,"Regular Toppings",in)){
                            input++;
                    }
                        break;
                    case "sauce":
                        if(!addRegularToppings(menu,sauces,4,"Sauces",in)){
                            input++;
                        }
                    case "complete":
                        Sandwich sandwich = new Sandwich(size,bread,meats,cheeses,regularT,sauces,runningTotal);
                        order.cart.add(sandwich);
                        System.out.println(cart);
                        System.out.println(String.format("Total:$%.2f", sandwich.getTotal()));
                        input++;
                        break;
                    default:
                        System.out.println("Thank you.");
                        break;
                }
            }
            catch (Exception error){
                System.out.println("Error");
            }
        }
    }
    private boolean addPremiumToppings(ArrayList<Topping> userToppings, Menu menu, int listNum, Scanner in, String type, int size, double[] priceList){
        System.out.println(String.format("%s\nSelect a %s type (enter the number next to the item):",type.toUpperCase(), type));
        ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
        menu.display(temp);
        System.out.print("Your input: ");
        Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
        userToppings.add(topping);
        temp.remove(topping);
        in.nextLine();
        System.out.print(String.format("Extra %s? (Y/N): ", type));;
        Boolean extraMeat = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        if(extraMeat) {
            runningTotal += priceList[size] + topping.getPrice();
        }
        else{runningTotal+= topping.getPrice();
        }
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore || temp.isEmpty();
    }
    private boolean addRegularToppings(Menu menu, ArrayList<Topping> userToppings,int listNum,String type,Scanner in){
        System.out.println(String.format("%s\nSelect a %s type (enter the number next to the item):",type.toUpperCase(), type));
        ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
        menu.display(temp);
        System.out.print("Your input: ");
        Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
        userToppings.add(topping);
        temp.remove(topping);
        in.nextLine();
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore || temp.isEmpty();
    }
    private double updatePrice(double cost){
        return cost;
    }

    }

