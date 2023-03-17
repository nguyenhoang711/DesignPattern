package Builder;

import Product.Car;
import Product.CarType;

public interface Builder {
    public void reset();

    public void setCarType(CarType type);

    public void setSeats(int seats);

    public void setEngine(String engine);

    public void setTripComputer(boolean haveComputer);

    public void setGPS(boolean haveGPS);

    public Car getCar();
}
