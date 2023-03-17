import Creator.ChicagoPizzaStore;
import Creator.NYPizzaStore;
import CreatorRevision.BankAccountFactory;
import CreatorRevision.Branch;
import Product.Pizza;
import ProductRevision.BankAccount;

public class CLient {
    public static void main(String[] args) {

        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        NYPizzaStore NYStore = new NYPizzaStore();
        Pizza pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
        pizza = NYStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");


        Branch localBranch = new Branch(new BankAccountFactory());
        BankAccount a1 = localBranch.getFactory().createAccount("B");
        System.out.println(a1);

        localBranch.getFactory().createAccount("C");
        System.out.println(localBranch.getFactory().getAccounts());
    }
}
