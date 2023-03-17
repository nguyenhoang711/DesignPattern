package Product;

import java.util.ArrayList;

public abstract class Pizza {
    String name;

    ArrayList<String> toppings = new ArrayList<>();

    public Pizza() {
    }

    public void prepare(){
        System.out.println("Preparing " + name);
        System.out.println("Them nuoc xot ...");
        //adding topping
        System.out.println("Adding topping: ");
        for(int i = 0;i<toppings.size();i++){
            System.out.println(" " + toppings.get(i));
        }
    }

    public void bake(){
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("cutting into 8 pieces");
    }

    public void box(){
        System.out.println("Put pizza into store box");
    }

    public String getName() {
        return name;
    }
}
