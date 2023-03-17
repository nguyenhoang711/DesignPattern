package Builder;

import ProductRevision.Meal;
import ProductRevision.WorkerMeal;

public class WorkerMealBuilder implements MealBuilder{
    private WorkerMeal workerMeal = new WorkerMeal();

    @Override
    public void buildDrink() {
        workerMeal.setDrink("Beer");
        System.out.println("Worker Drink: Beer");
    }

    @Override
    public void buildMain() {
        workerMeal.setMain("Fried Rice");
        System.out.println("Worker main: Fried Rice");
    }

    @Override
    public void buildDessert() {
        workerMeal.setDessert("Dragon Fruit");
        System.out.println("Worker Dessert: Dragon Fruit");
    }

    public void buildExtendFood(){
        workerMeal.setExtendFood("Lemonade");
        System.out.println("Worker Extended Food: Lemonade");
    }

    @Override
    public Meal getMeal() {
        return workerMeal;
    }
}
