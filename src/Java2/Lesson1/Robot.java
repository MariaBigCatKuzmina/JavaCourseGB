package Java2.Lesson1;

public class Robot implements Athletics{
    private final String model;
    private final int runningDistance;
    private final int jumpingHeight;

    public Robot(String model, int runningDistance, int jumpingHeight) {
        this.model = model;
        this.runningDistance = runningDistance;
        this.jumpingHeight = jumpingHeight;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Робот " + model;
    }

    @Override
    public int run() {
        return runningDistance;
    }

    @Override
    public int jump() {
        return jumpingHeight;
    }
}
