package Java2.Lesson1;

public class Cat implements Athletics{

    private final String name;

    private final int runningDistance;
    private final int jumpingHeight;

    public String getName() {
        return name;
    }

    public Cat(String name, int runningDistance, int jumpingHeight) {
        this.name = name;
        this.runningDistance = runningDistance;
        this.jumpingHeight = jumpingHeight;
    }

    @Override
    public String toString() {
        return "Котик " + name ;
    }

    @Override
    public int run(){
       return runningDistance;
    }

    @Override
    public int jump(){
        return jumpingHeight;
    }

}
