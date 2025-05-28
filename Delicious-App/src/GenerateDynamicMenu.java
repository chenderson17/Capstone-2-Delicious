import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateDynamicMenu {
    File f = new File("food.csv");
    Scanner reader;
    public void generate(int size, ArrayList<Topping> breadList, ArrayList<Topping> meatList, ArrayList<Topping> cheeseList, ArrayList<Topping> regularToppingList, ArrayList<Topping> sauces, ArrayList<Topping> sides, ArrayList<IMenuItem> drinks, ArrayList<IMenuItem> chips) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineReader = new Scanner(line);
                if (line.contains("Bread")) {

                    lineReader.next();
                    while (lineReader.hasNext()) {
                        breadList.add(new Bread(lineReader.next(), Prices.breadPrices[size]));
                    }

                } else if (line.contains("Meat")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        meatList.add(new Meat(lineReader.next(), Prices.meatPrices[size]));
                    }
                } else if (line.contains("Cheese")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        cheeseList.add(new Cheese(lineReader.next(), Prices.cheesePrices[size]));
                    }
                } else if (line.contains("Regular")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        regularToppingList.add(new RegularTopping(lineReader.next(), 0.00));
                    }
                } else if (line.contains("Sauces")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        sauces.add(new RegularTopping(lineReader.next(), 0.00));
                    }
                } else if (line.contains("Sides")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        sides.add(new RegularTopping(lineReader.next(), 0.00));
                    }
                } else if (line.contains("Soda")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        drinks.add(new Drink(lineReader.next(), Prices.drinkPrices[size]));
                    }
                }else if(line.contains("Chips")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        chips.add(new Chip(lineReader.next(),1.50));
                    }
                }

            }
        }
        catch (Exception error){
            System.out.println(error);
        }
        }
        //new topping line read
        private void add(Scanner lineReader, ArrayList<Topping> list, Topping t){
            lineReader.next();
            while(lineReader.hasNext()){
                list.add(t);
            }
        }

    }
