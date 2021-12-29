package Java2.Lesson2;

public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(int row, int col) {
        super("Ошибка преобразования типа String в тип int в ячейке входного массива [" + row + ", " + col + "]");
    }
}
