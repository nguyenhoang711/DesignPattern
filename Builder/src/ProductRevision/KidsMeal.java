package ProductRevision;

public class KidsMeal extends Meal{
    @Override
    public void giveFor() {
        System.out.println("For kids from 6-17 years old");
    }

    @Override
    public String toString() {
        return "KidsMeal{" + super.toString() + "}";
    }
}
