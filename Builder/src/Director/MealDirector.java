package Director;

import Builder.KidsMealBuilder;
import Builder.TeenagerMealBuilder;
import ProductRevision.KidsMeal;
import ProductRevision.TeenagerMeal;

public class MealDirector {
    public KidsMeal createKidsMeal(KidsMealBuilder builder){
        builder.buildDessert();
        builder.buildMain();
        builder.buildDrink();
        return (KidsMeal) builder.getMeal();
    }

    public TeenagerMeal createTeenagerMeal(TeenagerMealBuilder builder){
        builder.buildDessert();
        builder.buildMain();
        builder.buildDrink();
        builder.buildBrunch();
        return (TeenagerMeal) builder.getMeal();
    }
}
