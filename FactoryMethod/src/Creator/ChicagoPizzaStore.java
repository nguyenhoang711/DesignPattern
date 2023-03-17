package Creator;

import Product.*;

public class ChicagoPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new ChicagoStyleCheesePizza();
        }else if(type.equals("pepperoni")){
            return new ChicagoStylePepperoniPizza();
        }
        else return new ChicagoStyleVeggiePizza();
    }
}
