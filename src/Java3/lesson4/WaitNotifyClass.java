package Java3.lesson4;

public class WaitNotifyClass {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print(currentLetter);
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print(currentLetter);
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print(currentLetter);
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        WaitNotifyClass waitNotifyClass = new WaitNotifyClass();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                waitNotifyClass.printA();
            }
        });

        Thread threadB = new Thread(() -> {
            waitNotifyClass.printB();
        });

        Thread threadC = new Thread(waitNotifyClass::printC);

        threadA.start();
        threadB.start();
        threadC.start();
    }

}
