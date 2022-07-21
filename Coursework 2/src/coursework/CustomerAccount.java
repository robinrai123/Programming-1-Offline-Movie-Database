//By Robin
package coursework;

public class CustomerAccount implements Comparable<CustomerAccount> {

    private final String name;
    private final String surname;
    private Vehicle vehicle;
    private int balance;

    private enum DiscountType {
        NONE, STAFF, FRIENDS_AND_FAMILY
    };
    DiscountType discount;

    public CustomerAccount(String stringOfDetails) {
        //Takes a big string and splits it, creating a customer account object.
        //For the different vehicle and discount types a simple if equals string
        //is used.
        //This constructor sets all the names and what not, as well as making a 
        //vehicle of the right type, and activates any discounts
        String[] accountArray = stringOfDetails.split(",");
        this.name = accountArray[2];
        this.surname = accountArray[3];
        this.balance = Integer.parseInt(accountArray[6]);
        this.discount = DiscountType.NONE;
        //all of the above is the regular details for the account
        if (accountArray[0].equals("Car")) {
            this.vehicle
                    = new Car(
                            accountArray[1], accountArray[4],
                            Integer.parseInt(accountArray[5]));
        } else if (accountArray[0].equals("Van")) {
            this.vehicle
                    = new Van(
                            accountArray[1], accountArray[4],
                            Integer.parseInt(accountArray[5]));
        } else if (accountArray[0].equals("Truck")) {
            this.vehicle
                    = new Truck(
                            accountArray[1], accountArray[4],
                            Integer.parseInt(accountArray[5]));
        }
        //this stuff if for the vehicle for each customer account
        if (accountArray[7].equals("STAFF")) {
            activateStaffDiscount();
        } else if (accountArray[7].equals("FRIENDS_AND_FAMILY")) {
            activateFriendsAndFamilyDiscount();
            /*Setting the DiscountType to NONE and then using the setter 
            methods to change it is clunky, but that's what the spec says.*/
        }
        //this stuff changes the discount type if there is one
    }

    public void activateStaffDiscount() {
        this.discount = DiscountType.STAFF;
    }

    public void activateFriendsAndFamilyDiscount() {
        this.discount = DiscountType.FRIENDS_AND_FAMILY;
    }

    public void deactivateDiscount() {
        this.discount = DiscountType.NONE;
    }

    public void addFunds(int amount) {
        this.balance = this.balance + amount;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public int getBalance() {
        return this.balance;
    }

    public DiscountType getDiscount() {
        return this.discount;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.vehicle + " "
                + this.balance + " " + this.discount;
    }

    public int makeTrip() throws InsufficientAccountBalanceException /*
    - Simulates the customer making a trip on the toll road. calls correct
      calculateBasicTripCost and discounts 50% if staff and 10% if 
      friends/family. If there's enough balance it'll deduct the
      cost from the balance. If not it will throw a 
      InsufficientAccountBalanceException. 
     */ {
        int cost = this.vehicle.calculateBasicTripCost();

        if (this.discount == DiscountType.FRIENDS_AND_FAMILY) {
            cost = (int) (cost * 0.9);

        } else if (this.discount == DiscountType.STAFF) {
            cost = (int) (cost * 0.5);
        }

        if (this.balance >= cost) {
            this.balance = this.balance - cost;
            //System.out.println(cost);
            return cost;

        } else {
            throw new InsufficientAccountBalanceException();
        }

    }

    @Override
    public int compareTo(CustomerAccount other) {
        if (this.vehicle.getReg().compareTo(other.vehicle.getReg()) < 0) {
            return -1;
        } else if (this.vehicle.getReg().compareTo(other.vehicle.
                getReg()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
//It would return numbers bigger and smaller than 1/-1 deending on how much the
//plates differ by, so I forced it to be 1/-1.

//    public static void compareTest() {
//        System.out.println("\nCompare test:");
//        CustomerAccount John = new CustomerAccount("Car,AAAAAAA,John,Cena,"
//                + "Subaru,5,3000,NONE");
//        CustomerAccount Bob = new CustomerAccount("Car,AAAAAAA,Bob,Ross,"
//                + "Subaru,6,60000,STAFF");
//        int compareTo = John.vehicle.getReg().compareTo(Bob.vehicle.getReg());
//        System.out.println("Comparing: "+John.getVehicle());
//        System.out.println("With: "+Bob.getVehicle());
//        System.out.println("Result: " + compareTo);
//    }

//    public static void makeTripTest() {
//        System.out.println("\nmakeTrip test:");
//        CustomerAccount one = new CustomerAccount("Car,AAAAAAA,John,Cena,"
//                + "Subaru,5,3000,NONE");
//        System.out.println(one);
//        try {
//            System.out.println("Cost: "+one.makeTrip());
//        } catch (InsufficientAccountBalanceException e) {
//            System.out.println("NO MONEY");
//        }
//        CustomerAccount two = new CustomerAccount("Car,AAAAAAA,Bob,Ross,"
//                + "Subaru,5,3000,STAFF");
//        System.out.println(two);
//        try {
//            System.out.println("Cost: "+two.makeTrip());
//        } catch (InsufficientAccountBalanceException e) {
//            System.out.println("NO MONEY");
//        }
//        CustomerAccount three = new CustomerAccount("Car,AAAAAAA,John,Cena,"
//                + "Subaru,5,3000,FRIENDS_AND_FAMILY");
//        System.out.println(three);
//        try {
//            System.out.println("Cost: "+three.makeTrip());
//        } catch (InsufficientAccountBalanceException e) {
//            System.out.println("NO MONEY");
//        }
//    }
}
