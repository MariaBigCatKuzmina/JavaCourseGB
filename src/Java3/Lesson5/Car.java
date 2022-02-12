package Java3.Lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int carsCount;
    private static boolean isWinner;

    private static CyclicBarrier raceBarrier;
    private static CountDownLatch startFlag;
    private static CountDownLatch endFlag;

    private final Race race;

    private final int speed;
    private final String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        carsCount++;
        name = "Участник №" + carsCount;
        isWinner = false;
        raceBarrier = new CyclicBarrier(carsCount);
        startFlag = new CountDownLatch(carsCount);
        endFlag = new CountDownLatch(carsCount);
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s готовится%n", this.getName());
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.printf("%s готов%n", this.getName());
            startFlag.countDown();
            raceBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (!isWinner) {
            isWinner = true;
            System.out.println(getName() + " -- WINNER");
        }
        endFlag.countDown();
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public static CountDownLatch getStartFlag() {
        return startFlag;
    }

    public static CountDownLatch getEndFlag() {
        return endFlag;
    }
}
