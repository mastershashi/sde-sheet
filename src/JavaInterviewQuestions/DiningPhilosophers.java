import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private final ReentrantLock[] forks;
    private final Philosopher[] philosophers;

    public DiningPhilosophers(int numPhilosophers) {
        forks = new ReentrantLock[numPhilosophers];
        philosophers = new Philosopher[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
            philosophers[i] = new Philosopher(i);
        }

        // Start all philosophers
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
    }

    class Philosopher implements Runnable {
        private final int id;

        Philosopher(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                // Pick up left and right forks (avoiding deadlock)
                int leftFork = id;
                int rightFork = (id + 1) % forks.length;

                // Acquire left fork first if right fork is held by another philosopher
                if (forks[rightFork].isHeldByCurrentThread()) {
                    forks[leftFork].lock();
                } else {
                    forks[rightFork].lock();
                    forks[leftFork].lock();
                }

                // Eat
                System.out.println("Philosopher " + id + " is eating");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Put down forks
                forks[leftFork].unlock();
                forks[rightFork].unlock();

                // Think
                System.out.println("Philosopher " + id + " is thinking");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new DiningPhilosophers(5);
    }
}