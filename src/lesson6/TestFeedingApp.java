package lesson6;

import java.util.Random;

public class TestFeedingApp {
    public static void main(String[] args) {
        Cat[] cats = new Cat[5];
        Random rnd = new Random();
        for (int i = 0; i < cats.length; i++) {
          cats[i] = new Cat("Котик" + i, 0, rnd.nextInt(30));
        }
        Plate catsPlate = new Plate(rnd.nextInt(200));
        for (Cat hungryCat:cats) {
            hungryCat.catEatsFood(catsPlate);
        }
    }


}
