package Java3.Lesson5.stages;

import Java3.Lesson5.Car;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore tunnelSemaphore;

    public Tunnel(int tunnelCapacity) {
        this.length = 80;
        this.description = String.format("Туннель %d метров", this.length);
        tunnelSemaphore = new Semaphore(tunnelCapacity);
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.printf("%s готовится к этапу(ждет): %s%n", car.getName(), this.description);
                tunnelSemaphore.acquire();
                System.out.printf("%s начал этап: %s%n", car.getName(), this.description);
                Thread.sleep(this.length / car.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.err.printf("%s закончил этап: %s%n", car.getName(), this.description);
                tunnelSemaphore.release();
            }

        } catch (Exception e) {
            e.printStackTrace();
       }
    }
}
