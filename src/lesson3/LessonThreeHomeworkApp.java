package lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class LessonThreeHomeworkApp {
    public static void main(String[] args) {
        int[] intArray = {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0};
        System.out.println("Задание 1:");
        System.out.println("Исходный массив: " + Arrays.toString(intArray));
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == 0) {
                intArray[i] = 1;
            } else {
                intArray[i] = 0;
            }
        }
        System.out.println("Конечный массив: " + Arrays.toString(intArray));

        System.out.println("Задание 2:");
        int[] emptyIntArray = new int[100];
        for (int i = 0; i < emptyIntArray.length; i++) {
            emptyIntArray[i] = i + 1;
        }
        System.out.println(Arrays.toString(emptyIntArray));

        System.out.println("Задание 3:");
        int[] thirdArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Исходный массив: " + Arrays.toString(thirdArray));
        for (int i = 0; i < thirdArray.length; i++) {
            if (thirdArray[i] < 6) {
                thirdArray[i] *= 2;
            }
        }
        System.out.println("Конечный массив: " + Arrays.toString(thirdArray));

        System.out.println("Задание 4:");
        int[][] squareArray = new int[6][6];
        System.out.println("Исходный массив:");
        printArr(squareArray);
        squareArray = changeDiagonalsOfArray(squareArray, 1);
        System.out.println("Конечный массив:");
        printArr(squareArray);

        System.out.println("Задание 5:");
        int[] fithArray;
        fithArray = createAndFillArray(10, 5);
        System.out.println("Заполненный массив: \n" + Arrays.toString(fithArray));

        System.out.println("Задание 6:");
        int[] sixthArray;
        sixthArray = makeRandomArray(10);
        System.out.println(Arrays.toString(sixthArray));
        System.out.println("Минимальный элемент: "+findMinMaxElement(sixthArray,"min"));
        System.out.println("Максимальный элемент: "+findMinMaxElement(sixthArray,"max"));

        System.out.println("Задание 7:");
       // int[] balanceArr = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] balanceArr = {1, 1, 1, 2, 1};
        System.out.println(Arrays.toString(balanceArr));
        if (checkBalance(balanceArr)){
            System.out.println("есть баланс");
        }
        else {
            System.out.println("нет баланса");
        }
        System.out.println("Задание 8:");
        int[] shiftArr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Начальный массив: \n" + Arrays.toString(shiftArr));
        System.out.println("На сколько позиций сделать сдвиг массива?\n" +
                           "Если n отрицательное - сдвиг влево, положительное - вправо");
        Scanner scn = new Scanner(System.in);
        int shift;
        shift = scn.nextInt();

        shiftArr = cyclicShift(shiftArr, shift);
        System.out.println("Результат сдвига: \n" + Arrays.toString(shiftArr));
    }

    private static int findMinMaxElement(int[] arr, String isMinMax){
        int Min = arr[0];
        int Max = 0;
        for (int j : arr) {
            if (j < Min) {
                Min = j;
            }
            if (j > Max) {
                Max = j;
            }
        }
      //  TODO: Узнать в чем принципиальное отличие в сравнении строк методом  equals() от == и что лучше использовать
       //   if (isMinMax.toLowerCase().equals("min")){
        if (isMinMax.toLowerCase() == "min"){
            return Min;
        }
        return Max;
    }

    private static boolean checkBalance(int[] arr){
        int leftSum = 0;
        int rightSum;
        for (int i = 0; i < arr.length; i++) {
          leftSum += arr[i];
          rightSum = 0;
          for (int j = i + 1; j < arr.length; j++) {
              rightSum += arr[j];
          }
          if (leftSum == rightSum)
            return true;
        }
        return false;
    }

    private static int[] cyclicShift(int[] arr, int shift){
        int x;
        if (shift % arr.length == 0){
            return arr;
        }
        if (shift > arr.length) {
            shift = (shift % arr.length);
        }
        for (int i = 0; i < arr.length - shift; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                x = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = x;
            }
        }
        return arr;
    }
    private static int[] makeRandomArray(int len) {
        int[] outArray = new int[len];
        for (int i = 0; i < len; i++) {
            outArray[i] = (int) (Math.random()*100);
        }
        return outArray;
    }

    private static int[] createAndFillArray(int len, int initialValue) {
        int[] outArray = new int[len];
        for (int i = 0; i < len; i++) {
            outArray[i] = initialValue;
        }
        return outArray;
    }

    public static int[][] changeDiagonalsOfArray(int[][] arr, int value){
        int [][] a = arr;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if ((i == j) || (j == arr[i].length - 1 - i)) {
                    a[i][j] = value;
                }
            }
        }
        return a;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
        }
        System.out.println(); }
    }
}
