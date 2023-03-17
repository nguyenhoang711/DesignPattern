package ProductRevision;

public class EmployeeMeal extends Meal{
    private String brunch;

    @Override
    public void giveFor() {
        System.out.println("For anyone work in office");
    }

    public String getBrunch() {
        return brunch;
    }

    public void setBrunch(String brunch) {
        this.brunch = brunch;
    }

    @Override
    public String toString() {
        return "EmployeeMeal{" +
                "brunch='" + brunch + '\'' +
                super.toString() +
                "} ";
    }
}
