public class PrintInOrder2 {

    static class PrintFirst implements Runnable {
        private final Foo foo;

        PrintFirst(Foo foo) {
            this.foo = foo;
        }

        public void run() {
            try {
                foo.first(new Printer("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PrintSecond implements Runnable {
        private final Foo foo;

        PrintSecond(Foo foo) {
            this.foo = foo;
        }

        public void run() {
            try {
                foo.second(new Printer("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PrintThird implements Runnable {
        private final Foo foo;

        PrintThird(Foo foo) {
            this.foo = foo;
        }

        public void run() {
            try {
                foo.third(new Printer("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Printer implements Runnable {
        private final String text;

        Printer(String text) {
            this.text = text;
        }

        public void run() {
            System.out.print(text);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        Thread t1 = new Thread(new PrintFirst(foo));
        Thread t2 = new Thread(new PrintSecond(foo));
        Thread t3 = new Thread(new PrintThird(foo));

        t2.start();
        t3.start();
        t1.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println();
    }
}