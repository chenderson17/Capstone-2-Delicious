import javax.swing.text.DateFormatter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    Scanner in = new Scanner(System.in);
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
                                        4) View Cart
                                        5) Checkout
                                        0) Cancel Order
                                        Your Input: """);
                       orderInput = in.nextLine();
                       switch (orderInput){
                           case "0":
                               order.emptyCart();
                               System.out.println("*** Order Cancelled ***");
                               break;
                           case "1":
                               order.addToCart(orderASandwich());
                               break;
                           case "2":
                               addDrink(order);
                               break;
                           case "3":
                               addChips(order);
                               break;
                           case "4":
                               if(order.cart.isEmpty()){
                                   System.out.println("Cart is empty");
                               }
                               else {
                                   order.viewCart();
                               }
                               break;
                           case "5":
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
                in.nextLine();
            }
        }
    }
    public Sandwich orderASandwich(){
        int input = 0;
        int size = 0;
        Sandwich sandwich = null;
        String[] pages = {"s","b","m","c","r","sauce","sides","complete"};
        Menu menu = null;
        while(input < pages.length) {
            try {
                switch (pages[input]) {
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
                        sandwich.bread = (Bread) menu.getBreadList().get(in.nextInt() - 1);
                        in.nextLine();
                        sandwich.price += sandwich.bread.getPrice();
                        input++;
                        break;
                    case "m":
                        sandwich.price += addPremiumToppings(sandwich.meatToppings, menu, 1, "Meat", size, Prices.extraMeat, 0.0);
                        input++;
                        break;
                    case "c":
                        sandwich.price += addPremiumToppings(sandwich.cheeseToppings, menu, 2, "Cheese", size, Prices.extraCheese, 0.0);
                        input++;
                        break;
                    case "r":
                        addRegularToppings(menu, sandwich.regularToppings, 3, "Regular Toppings");
                        input++;
                        break;
                    case "sauce":
                        addRegularToppings(menu, sandwich.sauces, 4, "Sauces");
                        input++;
                        break;
                    case "sides":
                        addRegularToppings(menu, sandwich.sides, 5, "Sides");
                        input++;
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
            } catch (Exception error) {
                System.out.println(error);
                in.nextLine();
            }
        }
        return sandwich;
    }
    private double addPremiumToppings(ArrayList<Topping> userToppings, Menu menu, int listNum, String type, int size, double[] priceList, double t) {
            System.out.println(String.format("%s\nSelect a %s type (enter the number next to the item):", type.toUpperCase(), type));
            ArrayList<Topping> temp = (ArrayList<Topping>) menu.m.get(listNum);
            menu.display(temp);
            System.out.print("Your input: ");
            Topping topping = (Topping) ((ArrayList<?>) menu.m.get(listNum)).get(in.nextInt() - 1);
            temp.remove(topping);
            in.nextLine();
            System.out.print(String.format("Extra %s for %.2f? (Y/N): ", type, priceList[size]));
            Boolean extraTopping = in.nextLine().equalsIgnoreCase("Y") ? true : false;
            if (extraTopping) {
                topping.price += priceList[size];
                t += topping.getPrice();
            } else {
                t += topping.getPrice();
            }
            userToppings.add(topping);
            System.out.print(String.format("Add more %s (Y/N): ", type));
            Boolean addMore = in.nextLine().equalsIgnoreCase("Y") ? true : false;
        return addMore && !temp.isEmpty() ? addPremiumToppings(userToppings, menu, listNum, type, size, priceList,t) : t;
    }

    private boolean addRegularToppings(Menu menu, ArrayList<Topping> userToppings,int listNum,String type){
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
        return addMore && !temp.isEmpty() ? addRegularToppings(menu, userToppings,listNum,type) : false;
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
        System.out.println("CHECKOUT");
        order.viewCart();
        System.out.print("Press Y to Confirm or Press enter to cancel:");
        String input = in.nextLine();
        LocalDateTime d =  LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String formattedDate = d.format(formatter);
        if(input.equalsIgnoreCase("Y")){
            UUID uuid =  UUID.randomUUID();
            try{
                File file = new File(formattedDate + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("Receipt \n");
                for(Object item : order.cart){
                    if(item instanceof ArrayList<?>){
                        for(int index = 0; index < ((ArrayList<?>) item).size(); index++){
                            writer.write(((ArrayList<?>) item).get(index).toString().replace("[","").replace("]","").join("") + "\n");
                        }
                    }
                    else {
                        writer.write(item.toString() + "\n");
                    }
                }
                writer.write(String.format("Total:$%.2f",order.getRunningTotal()));
                writer.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

