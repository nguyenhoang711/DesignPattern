package Builder;

import Product.BusinessCar;
import Product.CarType;

public class BusinessCarBuilder implements Builder{
    private BusinessCar car;

    public BusinessCarBuilder() {
        this.car = new BusinessCar();
    }

    @Override
    public void reset() {
        this.car = new BusinessCar();
    }

    @Override
    public void setCarType(CarType type) {
        car.type = type;
    }

    @Override
    public void setSeats(int seats) {
        car.seats = seats;
    }

    @Override
    public void setEngine(String engine) {
        car.engine = engine;
    }

    @Override
    public void setTripComputer(boolean haveComputer) {
        car.tripComputer = haveComputer;
    }

    @Override
    public void setGPS(boolean haveGPS) {
        car.GPS =haveGPS;
    }

    @Override
    public BusinessCar getCar() {
        return this.car;
    }

}
