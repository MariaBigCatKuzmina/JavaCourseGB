package lesson6;

public class Plate {
    //protected int plateVolume;
    protected int foodAmount;

    public Plate(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void decFoodAmount(int decValue)
    {
        if (foodAmount > decValue)
            foodAmount -= decValue;
        else
            foodAmount = 0;
    }
}
