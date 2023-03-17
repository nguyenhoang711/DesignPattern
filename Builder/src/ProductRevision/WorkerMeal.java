package ProductRevision;

public class WorkerMeal extends Meal{
    private String extendFood;

    @Override
    public void giveFor() {
        System.out.println("For people work in factory or construction");
    }

    public String getExtendFood() {
        return extendFood;
    }

    public void setExtendFood(String extendFood) {
        this.extendFood = extendFood;
    }

    @Override
    public String toString() {
        return "WorkerMeal{" +
                "extendFood='" + extendFood + '\'' +
                super.toString() +
                "} ";
    }
}
