import java.util.concurrent.Semaphore;

public class EvenOddPrinterSemaphore {
    private static final Semaphore evenSemaphore = new Semaphore(0);
    private static final Semaphore oddSemaphore = new Semaphore(1);

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                try {
                    oddSemaphore.acquire();
                    System.out.println(i);
                    evenSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    evenSemaphore.acquire();
                    System.out.println(i);
                    oddSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

     

        oddThread.start();
        evenThread.start();
    }
}