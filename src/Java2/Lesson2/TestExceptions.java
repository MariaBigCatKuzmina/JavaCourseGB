package Java2.Lesson2;

public class TestExceptions {

    public static int convertAndSumArrayElements(String[][] stringArray) {
        if (stringArray.length != 4 || stringArray[0].length != 4) {
            throw new MyArraySizeException();
        } else {
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
    }

    public static void main(String[] args) {
        String[][] testArray = {{"11", "12", "13", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testConvertErrArray = {{"1e", "12", "1a", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};
        String[][] testSizeExceptionArray = {{"21", "22", "23", "24"}, {"31", "32", "33", "34"}, {"41", "42", "43", "44"}};

        try {
            System.out.println("Сумма элементов массива = " + convertAndSumArrayElements(testArray));
            System.out.println(convertAndSumArrayElements(testConvertErrArray));
        } catch (MyArrayDataException e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(convertAndSumArrayElements(testSizeExceptionArray));
        } catch (MyArraySizeException e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
