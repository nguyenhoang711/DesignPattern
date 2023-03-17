package Builder;

import Product.BusinessCar;
import Product.CarType;

public class Director {

    public Director() {
    }

    public void constructSportCar(BusinessCarBuilder builder){
        builder.reset();
        builder.setCarType(CarType.SPORTCAR);
        builder.setEngine("dong co phan luc");
        builder.setSeats(4);
        builder.setTripComputer(true);
        builder.setGPS(true);
    }

    public void constructSUVCar(BusinessCarBuilder builder){
        builder.reset();
        builder.setCarType(CarType.SUV);
        builder.setEngine("dong co don");
        builder.setSeats(6);
        builder.setTripComputer(true);
        builder.setGPS(false);
    }

    public void constructTarga(BusinessCarBuilder builder){
        builder.reset();
        builder.setCarType(CarType.TARGA);
        builder.setEngine("dong co toi gian");
        builder.setSeats(5);
        builder.setTripComputer(false);
        builder.setGPS(false);
    }

    public void constructPickup(VanCarBuilder builder){
        builder.reset();
        builder.setCarType(CarType.PICKUP);
        builder.setEngine("dong co voi");
        builder.setSeats(3);
        builder.setTripComputer(true);
        builder.setGPS(false);
    }

    public void constructMiniVan(VanCarBuilder builder){
        builder.reset();
        builder.setCarType(CarType.MINIVAN);
        builder.setEngine("dong co cui bap");
        builder.setSeats(4);
        builder.setTripComputer(true);
        builder.setGPS(true);
    }
}
