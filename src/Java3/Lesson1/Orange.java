package Java3.Lesson1;

public class Orange implements Fruit{

    private final double weight;

    public Orange(double weight) {
        this.weight = weight;
    }

    @Override
    public double getFruitWeight() {
        return weight;
    }
}
