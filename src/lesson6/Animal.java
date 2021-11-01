package lesson6;

import java.util.Objects;

public class Animal {
    protected String name;
    protected int swimLimit;
    protected int runLimit;

    public Animal(String name, int swimLimit, int runLimit) {
        this.name = name;
        this.swimLimit = swimLimit;
        this.runLimit = runLimit;
    }

    public String getName() {
        return name;
    }

    public int getSwimLimit() {
        return swimLimit;
    }

    public int getRunLimit() {
        return runLimit;
    }

    public void run(int distance){
        if (runLimit == 0){
            System.out.println(name + " не может бегать!");
            return;
        }
        if (distance <= runLimit)
            System.out.println(name + " пробежал(а) " + distance + "м.");
        else
            System.out.println(name + " не смог(ла) пробежать " + distance + "м.");
    }

    public void swim(int distance){
        if (swimLimit == 0){
            System.out.println(name + " не может плавать!");
            return;
        }
        if (distance <= swimLimit)
            System.out.println(name + " проплыл(а) " + distance + "м.");
        else
            System.out.println(name + " не смог(ла) проплыть " + distance + "м.");
    }

}

