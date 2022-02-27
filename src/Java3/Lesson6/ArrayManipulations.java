package Java3.Lesson6;

//Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
// Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
// идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
// иначе в методе необходимо выбросить RuntimeException.
// Написать набор тестов для этого метода (по 3-4 варианта входных данных).
// Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

import java.util.Arrays;


public class ArrayManipulations {

    public int[] getElementsAfterLastFour(int[] inArray) {

        for (int i = inArray.length - 1; i >= 0; i--) {
            if (inArray[i] == 4) {
                return Arrays.copyOfRange(inArray, i + 1, inArray.length);
            }
        }
        throw new RuntimeException("В массиве нет ни одного элемента равного 4");
    }

    // Написать метод, который проверяет состав массива из чисел 1 и 4.
    // Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    // Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    public boolean hasOneAndFour(int[] inArray) {
        boolean hasFour = false;
        boolean hasOne = false;
        for (int element : inArray) {
            if (element == 1) {
                hasOne = true;
            }
            if (element == 4) {
                hasFour = true;
            }
            if (hasFour && hasOne) {
                break;
            }
        }
        return hasOne & hasFour;
    }

}
