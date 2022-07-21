//By Robin
package coursework;

//Car subclass of vehicle. Overrides trip cost calculation.

public class Car extends Vehicle {

    private final int numberOfSeats;

    public Car(String reg, String make, int seats) {    //contructor for car which also uses vehicle's constructor since it is one
        super(reg, make);
        this.numberOfSeats = seats;
    }

    @Override
    public int calculateBasicTripCost() {       //overrides the method inherited from vehicle
        //returns 500 if number of seats is five or less, 600 if more than 5.
        if (numberOfSeats <= 5) {
            return 500;
        } else {
            return 600;
        }

    }

    public int getSeat() {
        return numberOfSeats;
    }
    @Override
    public String toString(){
        return this.getReg() +" "+ this.getMake() +" "+ this.numberOfSeats;
    }
}
