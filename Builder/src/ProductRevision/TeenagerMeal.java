package ProductRevision;

public class TeenagerMeal extends Meal{

    private String snack;

    @Override
    public void giveFor() {
        System.out.println("For teenager age range from 18 to 25");
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    @Override
    public String toString() {
        return "TeenagerMeal{" +
                "snack='" + snack + '\'' +
                super.toString() +
                "} ";
    }
}
