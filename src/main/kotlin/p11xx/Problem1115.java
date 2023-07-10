package p11xx;

import java.util.concurrent.Semaphore;

class Problem1115 {
    public static void main(String[] args) {
        new Problem1115().test();
    }

    public void test() {
        new FooBar(5);
    }

    class FooBar {
        private final Semaphore foo = new Semaphore(0);

        private final Semaphore bar = new Semaphore(0);

        private final int n;

        public FooBar(int n) {
            this.n = n;
            foo.release();
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                foo.acquire();

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                bar.acquire();

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                foo.release();
            }
        }
    }
}

