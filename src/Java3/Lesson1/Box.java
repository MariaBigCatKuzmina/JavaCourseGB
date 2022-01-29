package Java3.Lesson1;

import java.util.ArrayList;

public class Box<F extends Fruit> {
    private final ArrayList<F> fruits;

    public void add(F fruit) {
        this.fruits.add(fruit);
    }

    public double getBoxWeight() {
        double weight = 0f;
        for (F itm : fruits) {
            weight = weight + itm.getFruitWeight();
        }
        return weight;
    }

    public boolean isEqualWeights(Box<?> anotherBox) {
        return this.getBoxWeight() == anotherBox.getBoxWeight();
    }

    public int compareWeights(Box<?> anotherBox) {
        if (this.isEqualWeights(anotherBox)) {
            return 0;
        } else if (this.getBoxWeight() < anotherBox.getBoxWeight()) {
            return -1;
        }
        return 1;
    }

    public boolean pourInto(Box<F> anotherBox) {
        if (anotherBox != null) {
            for (F fruit : fruits) {
                anotherBox.add(fruit);
            }
            fruits.clear();
            return true;
        }
        return false;
    }

    public Box() {
        this.fruits = new ArrayList<>();
    }
}
