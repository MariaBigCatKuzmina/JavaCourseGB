package Java3.Lesson5.stages;

import Java3.Lesson5.Car;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car);
}
