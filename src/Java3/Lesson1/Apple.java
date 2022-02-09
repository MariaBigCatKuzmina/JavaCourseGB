package Java3.Lesson1;

public class Apple implements Fruit {

    private final double weight;

    @Override
    public double getFruitWeight() {
        return weight;
    }

    public Apple(double weight) {
        this.weight = weight;
    }
}
