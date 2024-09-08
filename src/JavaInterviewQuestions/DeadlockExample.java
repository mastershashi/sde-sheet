import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                lock1.lock();
                System.out.println("Thread 1 acquired lock1");
                Thread.sleep(1000);
                lock2.lock();
                System.out.println("Thread 1 acquired lock2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock2.lock();
                System.out.println("Thread 2 acquired lock2");
                Thread.sleep(1000);
                lock1.lock();
                System.out.println("Thread 2 acquired lock1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}