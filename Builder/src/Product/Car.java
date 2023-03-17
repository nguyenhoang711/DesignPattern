package Product;

public abstract class Car{

    public CarType type;

    public int seats;

    public String engine;

    public boolean tripComputer;

    public boolean GPS;

    public Car() {
    }

    public Car(CarType type,int seats, String engine, boolean tripComputer, boolean GPS) {
        this.type = type;
        this.seats = seats;
        this.engine = engine;
        this.tripComputer = tripComputer;
        this.GPS = GPS;
    }

    public Car(Car source){
        this.type = source.type;
        this.engine = source.engine;
        this.seats = source.seats;
        this.tripComputer = source.tripComputer;
        this.GPS = source.GPS;
    }

    public abstract Car clone();
}
