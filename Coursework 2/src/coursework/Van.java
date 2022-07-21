//By Robin
package coursework;

//Van subclass of vehicle. Overrides trip cost calculation.

public class Van extends Vehicle {  //inheritis vehicle's shit

    private final int payload;  //extra variable

    public Van(String reg, String make, int payload) {  //van's own constructor, uses some of vehicle's contructor since it is one
        super(reg, make);
        this.payload = payload;
    }

    @Override
    public int calculateBasicTripCost() {   //basic thottery
        //500 if the payload is less than or equal to 600KG;
        //750 if the payload is less than or equal to 800KG, 
        //but greater than 600KG;
        //1000 if the payload is greater than 800KG.
        if (payload <= 600) {
            return 500;
        } else if (payload > 600 && payload <= 800) {
            return 750;
        } else {
            return 1000;
        }

    }

    public int getPayload() {
        return payload;
    }
    @Override
    public String toString(){   //overrides Vehicle's toString method
        return this.getReg() +" "+ this.getMake() +" "+ this.payload;
    }
}

