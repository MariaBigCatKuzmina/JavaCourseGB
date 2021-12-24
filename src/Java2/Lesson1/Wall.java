package Java2.Lesson1;

public class Wall implements Barrier{
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean canOvercome(Athletics athlete){
        return athlete.getJumpHeight() >= height;
    }

    @Override
    public String toString() {
        return "Стена высотой " + height + " см.";
    }

}
