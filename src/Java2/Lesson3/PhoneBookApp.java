package Java2.Lesson3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class PhoneBookApp {
    public static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        fillPhoneBook();
       // phoneBook.printPhoneListForPerson("Иванов");
        phoneBook.printPhoneBook();
    }


    
    private static void fillPhoneBook() {
        phoneBook.add("Иванов", "22-12-33");
        phoneBook.add("Иванов", "20-10-44");
        phoneBook.add("Иванов", "22-12-33");
        phoneBook.add("Петров", "15-11-35");
        phoneBook.add("Сидоров", "45-55-55");
        phoneBook.add("Петров", "22-12-33");
    }
}
