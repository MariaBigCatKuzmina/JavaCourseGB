package lesson6;


public class AnimalTestApp {
    public static void main(String[] args) {
        int catCount = 0;
        int dogCount = 0;
        String catSuffix, dogSuffix;

        Animal[] animals = new Animal[4];

        animals[0] = new Cat("Зевс", 140);
        animals[1] = new Dog("Шарик", 250, 500);
        animals[2] = new Dog("Найда", 400, 800);
        animals[3] = new Cat("Айка", 300);

        for (Animal pet : animals) {
            pet.run(250);
            pet.swim(300);
            if (pet instanceof Cat)
                catCount ++;
            else
                dogCount++;
        }
        catSuffix =getSuffix(catCount);
        if (catSuffix.equals("к")) catSuffix = "e"+ catSuffix;
        dogSuffix = getSuffix(dogCount);
        System.out.println("В банде " + catCount + " кош" + catSuffix+ " и "
                           + dogCount + " соба"+ dogSuffix);


    }
    private static String getSuffix(int count){
        int mod = count % 10;
        if (mod >= 2 && mod <= 4)
           return "ки";
        else if (mod == 1)
            return "ка";
        else
            return "к";
    }
}
