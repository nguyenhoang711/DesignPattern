package Creator;


import Product.NYStyleCheesePizza;
import Product.NYStylePepperoniPizza;
import Product.NYStyleVeggiePizza;
import Product.Pizza;

public class NYPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new NYStyleCheesePizza();
        }else if(type.equals("pepperoni")){
            return new NYStylePepperoniPizza();
        }
        else return new NYStyleVeggiePizza();
    }
}
