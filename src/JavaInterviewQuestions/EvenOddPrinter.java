public class EvenOddPrinter {

    private final Object lock = new Object();
    private int count = 1;
    private final int MAX = 100; // Adjust the range as needed

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter();
        Thread evenThread = new Thread(printer.new EvenPrinter());
        Thread oddThread = new Thread(printer.new OddPrinter());

        evenThread.start();
        oddThread.start();
    }

    class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (count <= MAX) {
                synchronized (lock) {
                    while (count % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (count <= MAX) {
                        System.out.println("Even: " + count);
                        count++;
                        lock.notify();
                    }
                }
            }
        }
    }

    class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (count <= MAX) {
                synchronized (lock) {
                    while (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (count <= MAX) {
                        System.out.println("Odd: " + count);
                        count++;
                        lock.notify();
                    }
                }
            }
        }
    }
}