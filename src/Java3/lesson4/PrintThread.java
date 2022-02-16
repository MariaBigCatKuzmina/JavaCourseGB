package Java3.lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintThread {
    private int currentNum;
    private final int iterations;
    private final int lettersCount = Letters.values().length;

    private static final Object mon = new Object();

    public PrintThread(int iterations) {
        this.iterations = iterations;
    }

    private void print(int printNum) {
        synchronized (mon) {
            try {
                for (int i = 0; i < iterations; i++) {
                    while (currentNum != printNum) {
                        mon.wait();
                    }
                    System.out.print(Letters.values()[printNum]);
                    currentNum = (currentNum < lettersCount - 1) ? ++currentNum : 0;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printLetters() {
        ExecutorService executorService = Executors.newFixedThreadPool(lettersCount);
        for (int i = 0; i < lettersCount; i++) {
            int printNum = i;
            executorService.submit(() -> print(printNum));
        }
        executorService.shutdown();
    }

}
