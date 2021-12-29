package Java2.Lesson2;

public class TestExceptions {

    public static void isCorrectArrayDimension(String[][] stringArray) {
        for (String[] strings : stringArray) {
            if (stringArray.length != 4 || strings.length != 4) {
                throw new MyArraySizeException();
            }
        }
    }

    public static int convertAndSumArrayElements(String[][] stringArray) {
        isCorrectArrayDimension(stringArray);
        int sum = 0;
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] testArray = {{"11", "12", "13", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testConvertErrArray = {{"1e", "12", "1a", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testSizeExceptionArray = {{"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};

        try {
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testArray));
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testConvertErrArray));
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testSizeExceptionArray));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
