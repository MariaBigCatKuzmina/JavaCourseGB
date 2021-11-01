package lesson6;

public class Cat extends Animal {
    protected int appetite;

    public Cat(String name,  int runLimit, int appetite) {
        super(name, 0, runLimit);
        this.appetite = appetite;
    }
    public void decAppetite(int foodAmount){
        if (foodAmount < appetite)
            appetite -= foodAmount;
        else
            appetite = 0;
    }

    public void catEatsFood(Plate plate){
        int startFoodAmount = plate.getFoodAmount();
        if (startFoodAmount == 0) {
            System.out.println("Тарелка пуста, котику " + name + " не досталось еды =(");
            return;
        }
        else {
            plate.decFoodAmount(appetite);
            decAppetite(startFoodAmount);
        }
        int newFoodAmount = plate.getFoodAmount();
        if (appetite == 0 && newFoodAmount == 0)
            System.out.println("Котик " + name + " съел всю еду и наелся");
        else if (appetite > 0 && newFoodAmount == 0)
            System.out.println("Котик " + name + " съел всю еду, но все еще голоден. Ему бы еще " + appetite + " едениц еды");
        else if (appetite == 0 && newFoodAmount > 0)
            System.out.println("Котик " + name + " наелся, а в тарелке еще есть " + newFoodAmount + " едениц еды");

    }
}


