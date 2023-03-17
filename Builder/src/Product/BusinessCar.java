package Product;

public class BusinessCar extends Car{
    //SUV,SportCar, Targa
    public BusinessCar() { }

    public BusinessCar(CarType type, int seats, String engine, boolean tripComputer, boolean GPS) {
        super(type, seats, engine, tripComputer, GPS);
    }

    public BusinessCar(BusinessCar source) {
        super(source);
    }

    @Override
    public Car clone() {
        return new BusinessCar(this);
    }

    @Override
    public String toString() {
        return "BusinessCar{" +
                "type=" + type +
                ", seats=" + seats +
                ", engine='" + engine + '\'' +
                ", tripComputer=" + tripComputer +
                ", GPS=" + GPS +
                "} ";
    }
}
