import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    Scanner in = new Scanner(System.in);
    String userInput;
    //public double runningTotal = 0.0;
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
                   Order order = new Order();
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
                               order.emptyCart();
                               System.out.println("*** Order Cancelled ***");
                               break;
                           case "1":
                               orderASandwich(order);
                               break;
                           case "2":
                               addDrink(order);
                               break;
                           case "3":
                               addChips(order);
                               break;
                           case "5":
                               if(order.cart.isEmpty()){
                                   System.out.println("Cart is empty");
                               }
                               else {
                                   System.out.println(order.viewCart() + String.format("Total:$%.2f", order.runningTotal));
                               }
                               break;
                           case "6":
                               checkout(order);
                               orderInput = "0";
                               order.emptyCart();
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
    public void orderASandwich(Order order){
        int input = 0;
        int size = 0;
        Sandwich sandwich = null;
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
                        sandwich = new Sandwich(size);
                        input++;
                        break;
                    case "b":
                        menu = new Menu(size);
                        System.out.println("BREAD\nSelect a bread type (enter the number next to the bread):");
                        menu.display((ArrayList<Topping>) menu.m.get(0));
                        System.out.print("Your Input: ");
                        sandwich.bread = (Bread) menu.getBreadList().get(in.nextInt() -1);
                        in.nextLine();
                        order.addToTotal(sandwich.bread.getPrice());
                        input++;
                        break;
                    case "m":

                        if(!addPremiumToppings(sandwich.meatToppings,menu, 1,in,"Meat",size,Prices.extraMeat,order)){
                            input++;
                        };
                        break;
                    case "c":
                        if(!addPremiumToppings(sandwich.cheeseToppings, menu, 2, in, "Cheese", size, Prices.extraCheese,order)) {
                            input++;
                        }
                        break;
                    case "r":
                        if(!addRegularToppings(menu, sandwich.regularToppings,3,"Regular Toppings",in)){
                            input++;
                    }
                        break;
                    case "sauce":
                        if(!addRegularToppings(menu,sandwich.sauces,4,"Sauces",in)){
                            input++;
                        }
                        break;
                    case "sides":
                        if(!addRegularToppings(menu,sandwich.sides,5,"Sides",in)){
                            input++;
                        }
                        break;
                    case "complete":
                        System.out.print("Do you want the sandwich Toasted?(Y/N):");
                        sandwich.isToasted = in.nextLine().equalsIgnoreCase("Y") ? true : false;
                        input++;
                        break;
                    default:
                        System.out.println("Thank you.");
                        break;
                }
            }
            catch (Exception error){
                System.out.println(error.getStackTrace());
            }
        }
        order.addToCart(sandwich);
    }
    private boolean addPremiumToppings(ArrayList<Topping> userToppings, Menu menu, int listNum, Scanner in, String type, int size, double[] priceList, Order order){
        System.out.println(String.format("%s\nSelect a %s type (enter the number next to the item):",type.toUpperCase(), type));
        ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
        menu.display(temp);
        System.out.print("Your input: ");
        Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
        userToppings.add(topping);
        temp.remove(topping);
        in.nextLine();
        System.out.print(String.format("Extra %s? (Y/N): ", type));;
        Boolean extraTopping = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        if(extraTopping) {
            order.addToTotal(priceList[size] + topping.getPrice());
            order.addToCart(new IMenuItem(String.format("Extra %s:",topping.name),priceList[size]));
        }
        else{order.addToTotal(topping.getPrice());
        }
        System.out.print(String.format("Add more %s? (Y/N): ",type));
        Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore && !temp.isEmpty();
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
        return addMore && !temp.isEmpty();
    }
    private boolean addDrink(Order order) throws FileNotFoundException {
        System.out.print("What size drink? [1]Small [2] Medium [3]Large:");
        int size = in.nextInt() - 1;
        in.nextLine();
        Menu menu = new Menu(size);
        menu.display(menu.drinks);
        System.out.printf("Select a drink from the list: " );
        Drink drink = (Drink) menu.drinks.get(in.nextInt()-1);
        in.nextLine();
        order.addToCart(drink);
        System.out.print("Would you like to order another drink?: ");
        return in.nextLine().equalsIgnoreCase("Y")? addDrink(order):false;
    }
    private boolean addChips(Order order) throws FileNotFoundException {
        /* Refactor this to be more efficent */
        Boolean addMore = false;
        try {
            Menu menu = new Menu(1);
            menu.display(menu.chips);
            System.out.print("Select a chip from the list: ");
            Chip chip = (Chip) menu.chips.get(in.nextInt() - 1);
            order.addToCart(chip);
            in.nextLine();
            System.out.print("Would you like to add more chips?(Y/N):");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return in.nextLine().equalsIgnoreCase("Y") ? addChips(order) : false;
    }

    private void checkout(Order order){
        /**
         * • Checkout - display the order details and the price
         * o Confirm - create the receipt file and go back to the home screen
         * o Cancel - delete order and go back to the homescreen
         */
        System.out.println("CHECKOUT");
        System.out.println(order.viewCart());
        System.out.print("Press Y to Confirm or Press enter to cancel:");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("Y")){
            UUID uuid =  UUID.randomUUID();
            try{
                File file = new File(String.valueOf(uuid) + "-receipt.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("Receipt \n");
                for(Object item : order.cart){
                    writer.write(String.valueOf(item) +"\n");
                }
                writer.write(String.format("Total:%.2f",order.getRunningTotal()));
                writer.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

