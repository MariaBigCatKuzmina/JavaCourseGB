package Java2.Lesson3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordList {
    public static String[] wordArray = {"Cat", "Dog", "Monkey", "Dog", "Elephant", "Dog", "Monkey", "Capricorn",
            "Parrot", "Giraffe", "Crocodile", "Monkey", "Tiger", "Elephant"};

    public static void main(String[] args) {

        Map<String, Integer> rangedWordsArray = new HashMap<>();

        for (String word : wordArray) {
            Integer range = rangedWordsArray.get(word);
            if (range == null) {
                range = 0;
            }
            rangedWordsArray.put(word, ++range);
        }

        Iterator<Map.Entry<String, Integer>> itr = rangedWordsArray.entrySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }


}
