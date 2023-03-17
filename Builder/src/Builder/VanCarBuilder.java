package Builder;

import Product.Car;
import Product.CarType;
import Product.VanCar;

public class VanCarBuilder implements Builder{
    private VanCar vanCar;

    public VanCarBuilder() {
        this.vanCar = new VanCar();
    }

    @Override
    public void reset() {
        this.vanCar = new VanCar();
    }

    @Override
    public VanCar getCar() {
        return this.vanCar;
    }

    @Override
    public void setCarType(CarType type) {
        vanCar.type = type;
    }

    @Override
    public void setSeats(int seats) {
        vanCar.seats = seats;
    }

    @Override
    public void setEngine(String engine) {
        vanCar.engine = engine;
    }

    @Override
    public void setTripComputer(boolean haveComputer) {
        vanCar.tripComputer = haveComputer;
    }

    @Override
    public void setGPS(boolean haveGPS) {
        vanCar.GPS = haveGPS;
    }


}
