import java.util.concurrent.*;
/*
class Foo {
    // Replace with your actual solution
    private final Semaphore s1 = new Semaphore(0);
    private final Semaphore s2 = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        s1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        s1.acquire();
        printSecond.run();
        s2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        s2.acquire();
        printThird.run();
    }
}
*/

class Foo {
    volatile int count = 0;

    public Foo() { }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count < 1);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count < 2);
        printThird.run();
    }
}

public class PrintInOrder {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        Thread t1 = new Thread(() -> {
            try { foo.first(() -> System.out.print("first")); }
            catch (InterruptedException e) { e.printStackTrace(); }
        });

        Thread t2 = new Thread(() -> {
            try { foo.second(() -> System.out.print("second")); }
            catch (InterruptedException e) { e.printStackTrace(); }
        });

        Thread t3 = new Thread(() -> {
            try { foo.third(() -> System.out.print("third")); }
            catch (InterruptedException e) { e.printStackTrace(); }
        });

        // Start in reverse order to test synchronization
        t2.start();
        t3.start();
        t1.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(); // newline
    }
}