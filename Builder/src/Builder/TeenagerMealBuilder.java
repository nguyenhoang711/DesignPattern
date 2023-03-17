package Builder;

import ProductRevision.Meal;
import ProductRevision.TeenagerMeal;

public class TeenagerMealBuilder implements MealBuilder{
    private TeenagerMeal teenagerMeal = new TeenagerMeal();

    @Override
    public void buildDrink() {
        teenagerMeal.setDrink("Soft Drink");
        System.out.println("Teenager drink: soft drink");
    }

    @Override
    public void buildMain() {
        teenagerMeal.setMain("Curries Rice");
        System.out.println("Teenage Main: Curries Rice");
    }

    @Override
    public void buildDessert() {
        teenagerMeal.setDessert("Cereal");
        System.out.println("Teenager Dessert: Cereal");
    }

    public void buildBrunch(){
        teenagerMeal.setSnack("Tomatoes Snack");
        System.out.println("Teenager Snack: Tomatoes Snack");
    }

    @Override
    public Meal getMeal() {
        return teenagerMeal;
    }
}
