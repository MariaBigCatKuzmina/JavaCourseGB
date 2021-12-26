package Java2.Lesson2;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException() {
        super("Размерность массива должна быть 4х4");
    }
}
