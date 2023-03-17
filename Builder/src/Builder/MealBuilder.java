package Builder;

import ProductRevision.Meal;

public interface MealBuilder {
    public void buildDrink();

    public void buildMain();

    public void buildDessert();

    public Meal getMeal();
}
