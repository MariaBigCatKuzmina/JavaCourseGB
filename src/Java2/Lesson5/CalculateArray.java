package Java2.Lesson5;

import java.util.Arrays;

public class CalculateArray {
    static final int SIZE = 10000000;

    public static void main(String[] args) {
        float[] commonArray = new float[SIZE];
        Arrays.fill(commonArray, 1.0f);
        calculateWholeArray(commonArray);
        try {
            calculatePartialArray(commonArray);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void calculateWholeArray(float[] arr) {
        float[] localArr = Arrays.copyOf(arr, SIZE);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            localArr[i] = calculateNewValue(localArr[i], i);
        }

        System.out.printf("Время расчета значений элементов массива без потоков = %d мс.%n", System.currentTimeMillis() - startTime);
    }

    private static float calculateNewValue(float oldValue, int i) {
        return (float) (oldValue * Math.sin(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void calculatePartialArray(float[] arr) throws InterruptedException {
        int halfSize = SIZE / 2;
        float[] arrayPart1 = new float[halfSize];
        float[] arrayPart2 = new float[halfSize];
        float[] wholeArray = new float[SIZE];

        System.arraycopy(arr, 0, arrayPart1, 0, halfSize);
        System.arraycopy(arr, halfSize, arrayPart2, 0, halfSize);

        long startTime = System.currentTimeMillis();

        Thread part1Thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < halfSize; i++) {
                    arrayPart1[i] = calculateNewValue(arrayPart1[i], i);
                }
            }
        });

        Thread part2Thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < halfSize; i++) {
                    arrayPart2[i] = calculateNewValue(arrayPart2[i], i);
                }
            }
        });

        part1Thread.start();
        part2Thread.start();

        part1Thread.join();
        part2Thread.join();

        System.arraycopy(arrayPart1, 0, wholeArray, 0, halfSize);
        System.arraycopy(arrayPart2, 0, wholeArray, halfSize, halfSize);

        System.out.printf("Время расчета значений элементов массива в 2 потоках = %d мс.%n", System.currentTimeMillis() - startTime);
    }
}
