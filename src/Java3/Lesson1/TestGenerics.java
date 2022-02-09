package Java3.Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestGenerics {

    private static <T> T[] changeArrayElements(T[] originalArray, int i, int j) {
        T[] resultArr = Arrays.copyOf(originalArray, originalArray.length);
        if (i >= 0 && i < originalArray.length && j >= 0 && j < originalArray.length) {
            T pocketValue = resultArr[i];
            resultArr[i] = resultArr[j];
            resultArr[j] = pocketValue;
        }
        return resultArr;
    }

    private static <T> ArrayList<T> transformArrayIntoList(T[] originalArray) {
        return new ArrayList<> (Arrays.asList(originalArray));
    }

    public static void main(String[] args) {
        testChangeArrayElements();
        String[] stringArray = {"One", "Two", "Three", "Four", "Five"};
        List<String> listArray= transformArrayIntoList(stringArray);
        listArray.forEach(System.out::println);
    }

    private static void testChangeArrayElements() {
        Integer[] testArray = randomIntArrayCreate(10, 10);
        printIntegerArray(testArray);
        Integer[] newArray = changeArrayElements(testArray, 2, 5);
        printIntegerArray(newArray);
    }

    private static void printIntegerArray(Integer[] testArray) {
        for (Integer arrayItm : testArray) {
            System.out.printf("[%d] ", arrayItm);
        }
        System.out.println();
    }

    private static Integer[] randomIntArrayCreate(int length, int maxValue) {
        Integer[] resultArray = new Integer[length];
        Random rnd = new Random();
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = rnd.nextInt(maxValue);
        }
        return resultArray;
    }
}
