package Java2.Lesson1;

public class Wall implements Barrier{
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean canOvercome(Athletics athlete){
        return height <= athlete.jump();
    }

    @Override
    public String toString() {
        return "Стена высотой " + height + " см.";
    }

}
