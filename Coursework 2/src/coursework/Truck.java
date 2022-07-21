//By Robin
package coursework;

//Truck subclass of vehicle. Overrides trip cost calculation.

public class Truck extends Vehicle {

    private final int numTrailers;

    public Truck(String reg, String make, int trailers) {
        super(reg, make);
        this.numTrailers = trailers;
    }

    @Override
    public int calculateBasicTripCost() {
        //1250 if the truck has no trailer or a single trailer;
        //1500 if the truck has two or more trailers.
        if (numTrailers <= 1) {
            return 1250;
        } else {
            return 1500;
        }

    }

    public int getTrailers() {
        return numTrailers;
    }
    @Override
    public String toString(){
        return this.getReg() +" "+ this.getMake() +" "+ this.numTrailers;
    }
}
