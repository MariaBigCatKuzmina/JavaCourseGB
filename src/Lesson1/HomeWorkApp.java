package Lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {
        System.out.println("Задание 2:");
        printThreeWords();
        System.out.println();
        System.out.println("Задание 3:");
        checkSumSign();
        System.out.println();
        System.out.println("Задание 4:");
        printColor();
        System.out.println();
        System.out.println("Задание 5:");
        compareNumbers();
    }
    public static void printThreeWords()
    {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign()
    {
        int a = 5;
        int b = 8;
        if (a+b >= 0)
        {
            System.out.println("Сумма чисел " + a + " и " + b +" положительна");
        }
        else
        {
            System.out.println("Сумма чисел " + a + " и " + b +" отрицательна");
        }
    }

    public static void printColor()
    {
        int value=25;
        if (value <= 0)
        {
            System.out.println("Красный");
        }
        else if(value > 0 && value <= 100)
        {
            System.out.println("Желтый");
        }
        else
        {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers()
    {
        int a = 10;
        int b = 4;
        if (a >= b)
        {
            System.out.println("a >= b");
        }
        else
        {
            System.out.println("a < b");
        }
    }
}
