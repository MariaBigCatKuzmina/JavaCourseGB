package Java2.Lesson3;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> phoneBook = new TreeMap<>();

    public void add(String lastName, String phoneNumber) {
        Set<String> phoneList = phoneBook.get(lastName);
        if (phoneList == null) {
            phoneList = new HashSet<>();
        }
        phoneList.add(phoneNumber);
        this.phoneBook.put(lastName, phoneList);
    }

    public Set<String> get(String lastName) {
        return this.phoneBook.get(lastName);
    }

    public void printPhoneListForPerson(String lastName) {
        String phoneNumbers = "";
        Iterator<String> itr = get(lastName).iterator();
        while (itr.hasNext()) {
            phoneNumbers = phoneNumbers.equals("") ? itr.next() : phoneNumbers +", "+ itr.next();
        }
        System.out.printf("%s : (%s)%n", lastName, phoneNumbers);
    }

    public void printPhoneBook(){
        for (String phoneBookEntry : this.phoneBook.keySet()) {
            printPhoneListForPerson(phoneBookEntry);
        }
    }

}
