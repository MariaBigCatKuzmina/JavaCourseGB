package Java2.Lesson1;

public class Track implements Barrier{
    private final int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Трек длинной "+ length +"м.";
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean canOvercome(Athletics athlete){
        return athlete.run() >= length;
    }
}
