import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    Scanner in = new Scanner(System.in);
    String userInput;
    public double runningTotal = 0.0;
    ArrayList<Object> cart = new ArrayList<>();
    void run(){
        String userInput = "";
        while(!userInput.equalsIgnoreCase( "0")){
            try {
                /**ORDERING INTERFACE*/
                System.out.print("""
                        1) New Order
                        0) Press 0 to exit
                        Your Input:""");
                userInput = in.nextLine();
                String orderInput = "";
               if(userInput.equals("1")){
                   while(!orderInput .equals("0")){
                       System.out.print("""
                                        1) Order a Sandwich
                                        2) Add Drink
                                        3) Add Chips
                                        4) Checkout
                                        5) View Cart
                                        6) Checkout
                                        0) Cancel Order
                                        Your Input: """);
                       orderInput = in.nextLine();
                       switch (orderInput){
                           case "0":
                               cart.clear();
                               runningTotal = 0.0;
                               System.out.println("*** Order Cancelled ***");
                               break;
                           case "1":
                               orderASandwich();
                               break;
                           case "2":
                               orderDrink();
                               break;
                           case "3":
                               addChips();
                               break;
                           case "5":
                               if(cart.isEmpty()){
                                   System.out.println("Cart is empty");
                               }
                               else {
                                   System.out.println(cart + String.format("Total:$%.2f", runningTotal));
                               }
                               break;
                           case "6":
                               checkout();
                               orderInput = "0";
                               break;
                       }
                   }
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
        ArrayList<Topping> sides = new ArrayList<>();
        String[] pages = {"s","b","m","c","r","sauce","sides","complete"};
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
                        break;
                    case "sides":
                        if(!addRegularToppings(menu,sides,5,"Sides",in)){
                            input++;
                        }
                        break;
                    case "complete":
                        System.out.print("Do you want the sandwich Toasted?(Y/N):");
                        boolean isToasted = in.nextLine().equalsIgnoreCase("Y") ? true : false;
                        Sandwich sandwich = new Sandwich(size,bread,meats,cheeses,regularT,sides,sauces,runningTotal, isToasted);
                        cart.add(sandwich);
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
    private void orderDrink() throws FileNotFoundException {
        try {
            addDrink();
        }
        catch (Exception error){
            System.out.println(error);
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
            cart.add(String.format("Extra %s:$%.2f",topping.name,priceList[size]));
        }
        else{runningTotal+= topping.getPrice();
        }
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore && !temp.isEmpty();
    }
    private boolean addRegularToppings(Menu menu, ArrayList<Topping> userToppings,int listNum,String type,Scanner in){
        System.out.println(String.format("%s\nSelect a %s type (enter the number next to the item):",type.toUpperCase(), type));
        ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
        System.out.println(temp.isEmpty());
        menu.display(temp);
        System.out.print("Your input: ");
        Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
        userToppings.add(topping);
        temp.remove(topping);
        in.nextLine();
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore && !temp.isEmpty();
    }
    private boolean addDrink() throws FileNotFoundException {
        System.out.print("What size drink? [1]Small [2] Medium [3]Large:");
        int size = in.nextInt() - 1;
        in.nextLine();
        Menu menu = new Menu(size);
        menu.display(menu.drinks);
        System.out.printf("Select a drink from the list: " );
        Drink drink = (Drink) menu.drinks.get(in.nextInt()-1);
        in.nextLine();
        cart.add(drink);
        runningTotal+= drink.price;
        System.out.print("Would you like to order another drink?: ");
        return in.nextLine().equalsIgnoreCase("Y")? addDrink():false;
    }
    private boolean addChips() throws FileNotFoundException {
        /* Refactor this to be more efficent */
        Boolean addMore = false;
        try {
            Menu menu = new Menu(1);
            menu.display(menu.chips);
            System.out.print("Select a chip from the list: ");
            Chip chip = (Chip) menu.chips.get(in.nextInt() - 1);
            cart.add(chip);
            in.nextLine();
            runningTotal+= chip.price;
            System.out.print("Would you like to add more chips?(Y/N)");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return in.nextLine().equalsIgnoreCase("Y") ? addChips() : false;
    }

    private void checkout(){
        /**
         * • Checkout - display the order details and the price
         * o Confirm - create the receipt file and go back to the home screen
         * o Cancel - delete order and go back to the home screen
         */
        System.out.println("CHECKOUT");
        System.out.println(cart);
        System.out.print("Press Y to Confirm or Press enter to cancel:");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("Y")){
            UUID uuid =  UUID.randomUUID();
            try{
                File file = new File(String.valueOf(uuid) + "-receipt.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("Receipt \n");
                for(Object item : cart){
                    writer.write(String.valueOf(item) +"\n");
                }
                writer.write(String.format("Total:%.2f",runningTotal));
                writer.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    }

