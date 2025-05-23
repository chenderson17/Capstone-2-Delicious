import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateDynamicMenu {
    File f = new File("food.csv");
    Scanner reader;
    public void generate(int size, ArrayList<Bread> breadList, ArrayList<Meat> meatList, ArrayList<Cheese> cheeseList, ArrayList<RegularTopping> regularToppingList, ArrayList<RegularTopping> sauces, ArrayList<RegularTopping> sides) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineReader = new Scanner(line);
                if (line.contains("Bread")) {
                    lineReader.next();
                    while (lineReader.hasNext()) {
                        breadList.add(new Bread(lineReader.next(),Prices.breadPrices[size]));
                    }
                }
                else if(line.contains("Meat")){
                    lineReader.next();
                    while(lineReader.hasNext()){
                        meatList.add(new Meat(lineReader.next(), Prices.meatPrices[size]));
                    }
                }
                else if(line.contains("Cheese")){
                    lineReader.next();
                    while(lineReader.hasNext()){
                        cheeseList.add(new Cheese(lineReader.next(), Prices.cheesePrices[size]));
                    }
                }
                else if(line.contains("Regular")){
                    lineReader.next();
                    while(lineReader.hasNext()){
                        regularToppingList.add(new RegularTopping(lineReader.next(),0.00));
                    }
                }
                else if(line.contains("Sauces")){
                    lineReader.next();
                    while(lineReader.hasNext()){
                        sauces.add(new RegularTopping(lineReader.next(),0.00));
                    }
                }
                else if(line.contains("Sides")){
                        lineReader.next();
                        while(lineReader.hasNext()){
                            sides.add(new RegularTopping(lineReader.next(),0.00));
                        }
                    }
                }
            }
        catch (Exception error){
            System.out.println(error);
        }
        }

    }
