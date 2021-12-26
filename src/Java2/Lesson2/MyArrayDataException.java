package Java2.Lesson2;

public class MyArrayDataException extends RuntimeException {
    int col;
    int row;

    public MyArrayDataException(int row, int col) {
        super("Ошибка преобразования типа String в тип int в ячейке входного массива [" + row + ", " + col + "]");
        this.col = col;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
