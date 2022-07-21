//By Robin
package coursework;

//Uncomment for Test harnesses.
//import static coursework.CustomerAccount.*;
//import static coursework.TollRoad.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TollRoadMain {

    public TollRoad initialiseTollRoadFromFile()
    /*Should have a string in here for the filepath, but spec. Test uses it as
            if it were there.*/
            /* 
    - Creates a new TollRoad. Reads through customerData.txt
      and populates the road with new CustomerAccount objects. 
    - File in this order;
          vehicleType, regNum, firstName, lastName, vehicleMake, 
          vehicleInfo (seatno, payload), startingBalance, discountType
    - Each customer is seperated by # and info split by ","
    - Method ends returning TollRoad object */ {
        try {
            File file = new File("customerData.txt");
            Scanner textScanner = new Scanner(file);

            String text = textScanner.next();
            String[] stringOfDetails = text.split("#"); //takes the text file and splits it for individual customers

            List<String> textArray; //makes a string array
            textArray = Arrays.asList(stringOfDetails); //each item is a customer

            TollRoad tollRoad = new TollRoad(); //makes a new tollroad
            for (String string : textArray) {   //for every item in the string array
                CustomerAccount temp = new CustomerAccount(string); //make a new customer account with each item, then add it to the tollroad
                tollRoad.addCustomer(temp);
            }

            return tollRoad;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        }

        return null;

    }

    public void simulateFromFile(TollRoad road) 
    /*Should have a string in here for the filepath, but spec. Test uses it as
            if it were there.*//*
    - Method reads transactions.txt and carries out the instructions written
    - File in this format;
        - addFunds, <registrationNumber>, <amount> $
            - adds funds to specifies account
                  - If successful, print <registrationNumber>: <amount> added 
                    successfuly
                  - If failed, print <registrationNumber>: addFunds failed. 
                    <message specific to caught exception>
            - Method should go through entire file
        - makeTrip, <registrationNumber>$
            - Attempts a trip
                - If successful, print <registrationNumber>: Trip completed 
                  sucessfully.
                - If failed, print <registrationNumber>: makeTrip failed. 
                  <message specific to caught exception>
            - Method should go through entire file*/ {
        try {
            File filmFile = new File("transactions.txt");
            Scanner scanner = new Scanner(filmFile);

            String text = scanner.next();
            String[] stringOfInstructions = text.split("\\$");  //string array, each item is an instruction
            List<String> instructionsArray;
            instructionsArray = Arrays.asList(stringOfInstructions);    //puts it in a list for some reason

            for (int x = 0; x < instructionsArray.size(); x++) {    //for every item in the array
                String[] accountArray = stringOfInstructions[x].split(","); //split the instruction down
                if ("addFunds".equals(accountArray[0])) {   //if the instruction is add funds

                    try {
                        CustomerAccount temp
                                = road.findCustomer(accountArray[1]);   //find the customer with the same plate
                        temp.addFunds(Integer.parseInt(accountArray[2]));   //add the funds
                        System.out.println(
                                accountArray[1] + ": " + accountArray[2]
                                + " added successfully");

                    } catch (CustomerNotFoundException e) {
                        System.out.println(
                                accountArray[1] + ": addFunds failed."
                                + " CustomerAccount does not exist");
                    }
                } else if ("makeTrip".equals(accountArray[0])) {        //if the instruction is make a trip

                    try {
                        road.chargeCustomer(accountArray[1]);   //run the chargecustomer method, which uses the findcustomer method and what not
                        System.out.println(accountArray[1]
                                + ": Trip completed successfully");

                    } catch (CustomerNotFoundException e) {
                        System.out.println(
                                accountArray[1]
                                + ": makeTrip failed. CustomerAccount does "
                                + "not exist");
                    } catch (InsufficientAccountBalanceException e) {
                        System.out.println(
                                accountArray[1]
                                + ": makeTrip failed. Insufficient funds");
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        }

    }

    public static void main(String[] args) /*
    - Simply calls initialiseTollRoadFromFile, then simulateFromFile with the 
      TollRoad. Also prints moneyMade during the simulation.
     */ {
        TollRoadMain main = new TollRoadMain(); //Make a new main
        TollRoad tollRoad = main.initialiseTollRoadFromFile();  //make a new tollroad with the initialisefromfile function
        main.simulateFromFile(tollRoad);    //simulate the tollroadfromfile function
        System.out.println("Total money made: " + tollRoad.getMoneyMade()); //prints the money made

//      Test harnesses
//      compareTest();
//      makeTripTest();
//      chargeCustomerTest();
//      Main test
//      TollRoadMain main = new TollRoadMain();
//      System.out.println("\n*TESTTESTTEST*");
//      TollRoad testRoad = main.
//              initialiseTollRoadFromFile("customerDataTest.txt");
//      main.simulateFromFile(testRoad, "transactionTest.txt");
    }

}
