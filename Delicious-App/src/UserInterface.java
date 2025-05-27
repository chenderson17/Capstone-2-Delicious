import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class UserInterface {
    Scanner in = new Scanner(System.in);
    String userInput;


    void run(){
        String userInput = "";
        while(!userInput.equalsIgnoreCase( "0")){
            try {
                System.out.print("""
                        1) Press 1 to order
                        0) Press 0 to exit
                        User Input:""");
                userInput = in.nextLine();
                switch (userInput){
                    case "1":
                        orderASandwich(in);
                        break;
                }
            }
            catch (Exception error){
                System.out.println("Error: " + error + "Please try again.");
            }
        }
    }
    public void orderASandwich(Scanner in){

        int input = 0;
        int size = 0;
        Bread bread = null;
        ArrayList<Topping> meats = new ArrayList<>();
        ArrayList<Topping> cheeses = new ArrayList<>();
        String[] pages = {"s","b","m","c","r"};
        Menu menu = null;
        double runningTotal = 0.00;
        //
        while(true) {
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
                        if(!addPremiumToppings(meats,menu, 1,in,"Meat",runningTotal,size,Prices.extraMeat,input)){
                           input++;
                        };
                        break;
                    case "c":
                        if(!addPremiumToppings(cheeses, menu, 2, in, "Cheese",runningTotal, size, Prices.extraCheese,input)) {
                            input++;
                        }
                        break;
                    case "r":
                        System.out.println("Add regular toppings");
                        String pause = in.nextLine();
                        break;
                    default:
                        System.out.println("Sorry, I didn't understand that");
                        break;
                }
                System.out.println(String.format("Running Total %.2f", runningTotal));
                System.out.println(meats);
            }
            catch (Exception error){
                System.out.println("Error");
            }
        }
    }
    private boolean addPremiumToppings(ArrayList<Topping> userToppings, Menu menu, int listNum, Scanner in, String type, double runningTotal, int size, double[] priceList, int input){
        System.out.println(String.format("%s\n Select a %s type (enter the number next to the meat):",type.toUpperCase(), type));
        ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
        menu.display(temp);
        System.out.print("Your input: ");
        Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
        userToppings.add(topping);
        temp.remove(topping);
        in.nextLine();
        System.out.print(String.format("Extra %s? (Y/N): ", type));;
        Boolean extraMeat = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        runningTotal+= extraMeat? priceList[size] + topping.getPrice() : topping.getPrice();
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore;
    }

    }

