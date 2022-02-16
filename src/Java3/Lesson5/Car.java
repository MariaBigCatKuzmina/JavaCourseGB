package Java3.Lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int carsCount;
    private static final AtomicBoolean isWinner = new AtomicBoolean(false);

    private static CyclicBarrier raceBarrier;
    private static CountDownLatch endFlag;

    private final Race race;

    private final int speed;
    private final String name;

    public Car(Race race, int speed, CyclicBarrier startBarrier) {
        this.race = race;
        this.speed = speed;
        carsCount++;
        name = "Участник №" + carsCount;
        raceBarrier = startBarrier;
        endFlag = new CountDownLatch(carsCount);
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s готовится%n", this.getName());
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.printf("%s готов%n", this.getName());
            raceBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (!isWinner.get()) {
            isWinner.set(true);
            System.out.println(getName() + " -- WINNER");
        }
        endFlag.countDown();
    }

    public int getSpeed() {
        return speed;
    }

    public synchronized String getName() {
        return name;
    }

    public static CountDownLatch getEndFlag() {
        return endFlag;
    }
}
