package lesson2;

public class SecondHomeWorkApp {

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        System.out.println(checkSum(20,5));
        System.out.println("Задание 2:");
        printIfPositiveNegative(-19);
        System.out.println("Задание 3:");
        System.out.println(ifPositiveNegative(10));
        System.out.println("Задание 4:");
        printStrNTimes("Test", 5);
        System.out.println("Задание 5:");
        printIfLeapYear(2020);

    }

    public static boolean checkSum(int a, int b)
    {
        if (a + b >= 10 && a + b <= 20)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void printIfPositiveNegative(int value)
    {
        if (value >= 0)
        {
            System.out.println("Число " + value + " положительное" );
        }
        else
        {
            System.out.println("Число " + value + " отрицательное" );
        }
    }


    public static boolean ifPositiveNegative(int value)
    {
        if (value >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void printStrNTimes(String str, int n)
    {
        for (var i=0; i < n; i++)
        {
            System.out.println(str);
        }
    }

    public static void printIfLeapYear(int year)
    {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            System.out.println("Год " + year + " високосный");
        else
            System.out.println("Год " + year + " не високосный");

    }
}
