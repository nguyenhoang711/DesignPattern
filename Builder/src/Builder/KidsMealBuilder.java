package Builder;

import ProductRevision.KidsMeal;
import ProductRevision.Meal;

public class KidsMealBuilder implements MealBuilder{
    KidsMeal kidsMeal = new KidsMeal();

    @Override
    public void buildDrink() {
        kidsMeal.setDrink("Milk");
        System.out.println("Kid Drink: Milk");
    }

    @Override
    public void buildMain() {
        kidsMeal.setMain("Soup");
        System.out.println("Kid Main: Soup");
    }

    @Override
    public void buildDessert() {
        kidsMeal.setDessert("Bananas");
        System.out.println("Kid desert: Bananas");
    }

    @Override
    public Meal getMeal() {
        return kidsMeal;
    }
}
