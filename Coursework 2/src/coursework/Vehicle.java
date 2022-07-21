//By Robin
package coursework;

//Superclass of all vehicle types.

public abstract class Vehicle { //abstract - means a vehicle object itself isn't happening, only stuff that inherits it is.

    final private String reg;
    final private String make;

    Vehicle(String reg, String make) {  //simple constructor
        this.reg = reg;
        this.make = make;
    }

    abstract int calculateBasicTripCost();  //Yo all stuff that inherits vehicle will have its own calculateBasicTripCost

    public final String getReg() {  //unless overridden all things that inherit vehicle will have this method that does this
        return this.reg;
    }

    public final String getMake() {
        return this.make;
    }
    @Override
    public String toString(){
        return this.reg +" "+ this.make;
    }
}
