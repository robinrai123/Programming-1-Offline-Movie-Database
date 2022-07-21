//By Robin
package coursework;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TollRoad {

    private final ArrayList<CustomerAccount> customerAccounts;  //arrayList of customerAccount objects
    private int moneyMade;  //Total money made counter

    TollRoad() {    //constructor for a new TollRoad object
        moneyMade = 0;  //sets money to zero
        customerAccounts = new ArrayList<>();   //makes a new arrayList of customerAccounts
    }

    int getMoneyMade() {
        return this.moneyMade;
    }

    List getAccounts() {
        return this.customerAccounts;
    }

    void addCustomer(CustomerAccount acc) { //takes a given customerAccount and adds it to the arrayList
        customerAccounts.add(acc);
    }

    CustomerAccount findCustomer(String regNum) throws  //
            CustomerNotFoundException { //can throw this exception if it can't find it
        for (CustomerAccount account : customerAccounts) {  //for every customerAccount in the arrayList
            if (account.getVehicle().getReg().equals(regNum)) { //if the customerAccount's reg equals the string
                return account; //return the customerAccount
            }
        }
        throw new CustomerNotFoundException();  //if there isn't a match, throw the exception
    }

    void chargeCustomer(String regNumber) throws CustomerNotFoundException,
            InsufficientAccountBalanceException /*
    - looks for account using findCustomer(), and uses method makeTrip() or 
      CustomerNotFoundExcpetion. Adds cost of trip to moneyMade or
      InsufficientAccountBalanceExcpetion*/ {
        CustomerAccount temp = findCustomer(regNumber); //looks for customer with findCustomer method and calls it temp
        int cost = temp.makeTrip(); //runs make trip on temp which returns the int
        this.moneyMade = this.moneyMade + cost; //adds the int onto total money made

    }

//tests findCustomer() as well ChargCustomer() and TollRoad().
//    public static void chargeCustomerTest() {
//        System.out.println("\nChargeCustomer test:");
//        TollRoad road = new TollRoad();
//        CustomerAccount john = new CustomerAccount("Car,AAAAAAA,John,Cena,"
//                + "Subaru,5,3000,NONE");
//        System.out.println(john);
//        road.addCustomer(john);
//        CustomerAccount bob = new CustomerAccount("Car,MAMAMIA,Here,"
//                + "Wegoagain,Subaru,5,3000,NONE");
//        System.out.println(bob);
//        road.addCustomer(bob);
//        try {
//            System.out.println("Found: " + road.findCustomer("MAMAMIA"));
//        } catch (CustomerNotFoundException e) {
//            System.out.println("Can't find it");
//        }
//        try {
//            road.chargeCustomer("AAAAAAA");
//            System.out.println("Charging customer with plate AAAAAAA");
//        } catch (CustomerNotFoundException ex) {
//            System.out.println("Can't find it");
//        } catch (InsufficientAccountBalanceException ex) {
//            System.out.println("Not enuf dosh");
//        }
//        System.out.println("Total money made: " + road.moneyMade);
//    }
}
