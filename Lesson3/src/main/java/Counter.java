import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int count;

    public void incrementCount(String name) {
        System.out.printf("%s: %d%n", name, ++count);
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Incrementer(counter, locker));
            thread.setName(String.format("Incrementer %d", i));
            thread.start();
        }
    }

}

class Incrementer implements Runnable {
    private final ReentrantLock locker;
    private final Counter counter;

    @Override
    public void run() {
        while (true) {
            if (locker.tryLock())
            try {
                counter.incrementCount(Thread.currentThread().getName());
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }
        }
    }

    public Incrementer (Counter counter, ReentrantLock locker) {
        this.counter = counter;
        this.locker = locker;
    }
}
