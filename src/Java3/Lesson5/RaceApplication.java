package Java3.Lesson5;

import Java3.Lesson5.stages.Road;
import Java3.Lesson5.stages.Tunnel;

import java.util.concurrent.*;

public class RaceApplication {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        ExecutorService raceThreads = Executors.newFixedThreadPool(CARS_COUNT);

        for (int i = 0; i < CARS_COUNT; i++) {
            raceThreads.submit(cars[i]);
        }

        Car.getStartFlag().await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        Car.getEndFlag().await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        raceThreads.shutdown();
    }
}
