import Builder.*;
import Product.BusinessCar;
import Product.VanCar;

import Director.MealDirector;
import ProductRevision.KidsMeal;
import ProductRevision.Meal;
import ProductRevision.TeenagerMeal;

public class Client {
    public static void main(String[] args) {
//        VanCarBuilder a = new VanCarBuilder();
//        BusinessCarBuilder b = new BusinessCarBuilder();
//        Director nguyen = new Director();
//        //chỉ cách làm cho thằng a về việc tạo ra 1 xe tải
//        nguyen.constructPickup(a);
//
//        VanCar vanCar = a.getCar();
//        System.out.println(vanCar);
//
//
//        //chỉ thằng b cách tạo xe tải
//        nguyen.constructSportCar(b);
//        BusinessCar businessCar = b.getCar();
//        System.out.println(businessCar);

        //Meal
        MealDirector director = new MealDirector();
        Meal kidsMeal = director.createKidsMeal(new KidsMealBuilder());
        Meal teenagerMeal = director.createTeenagerMeal(new TeenagerMealBuilder());
        System.out.println(teenagerMeal);
        System.out.println(kidsMeal);
    }
}
