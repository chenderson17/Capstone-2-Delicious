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
            }
            catch (Exception error){
                System.out.println("Error: " + error + "Please try again.");
            }
        }
    }
    public void orderASandwich(){

    }

}
