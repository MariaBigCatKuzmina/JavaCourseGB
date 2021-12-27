package Java2.Lesson2;

public class TestExceptions {

    public static boolean isCorrectArrayDimension(String[][] stringArray) {
        for (String[] strings : stringArray) {
            if (stringArray.length != 4 || strings.length != 4) {
                throw new MyArraySizeException();
            }
        }
        return true;
    }
    public static int convertAndSumArrayElements(String[][] stringArray) {
        if (isCorrectArrayDimension(stringArray)) {
            int i = 0, j = 0;
            try {
                int sum = 0;
                for (i = 0; i < stringArray.length; i++) {
                    for (j = 0; j < stringArray[i].length; j++) {
                        sum = sum + Integer.parseInt(stringArray[i][j]);
                    }
                }
                return sum;
            } catch (NumberFormatException e) {
                throw new MyArrayDataException(i, j);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[][] testArray = {{"11", "12", "13", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testConvertErrArray = {{"1e", "12", "1a", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testSizeExceptionArray = {{"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};

        try {
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testArray));
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testConvertErrArray));
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testSizeExceptionArray));
        } catch (MyArrayDataException | MyArraySizeException e ) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
