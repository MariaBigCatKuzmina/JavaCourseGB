package Java2.Lesson1;

public class Human implements Athletics{
    private String name;

    private final int runningDistance;
    private final int jumpingHeight;

    public Human(String name, int runningDistance, int jumpingHeight) {
        this.name = name;
        this.runningDistance = runningDistance;
        this.jumpingHeight = jumpingHeight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Человек " + name;
    }

    @Override
    public int getRunDistance(){
        return runningDistance;
    }

    @Override
    public int getJumpHeight() {
        return jumpingHeight;
    }
}
