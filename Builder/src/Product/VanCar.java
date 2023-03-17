package Product;

public class VanCar extends Car{
    //van,pickup,minivan,campervan
    public VanCar() {
    }

    public VanCar(CarType type, int seats, String engine, boolean tripComputer, boolean GPS) {
        super(type, seats, engine, tripComputer, GPS);
    }

    public VanCar(VanCar source) {
        super(source);
    }

    @Override
    public Car clone() {
        return new VanCar(this);
    }

    @Override
    public String toString() {
        return "VanCar{" +
                "type=" + type +
                ", seats=" + seats +
                ", engine='" + engine + '\'' +
                ", tripComputer=" + tripComputer +
                ", GPS=" + GPS +
                "} ";
    }
}
