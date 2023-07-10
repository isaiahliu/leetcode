package p11xx;

import java.util.concurrent.Semaphore;

class Problem1114 {
    public static void main(String[] args) {
        new Problem1114().test();
    }

    public void test() {
        new Foo();
    }

    class Foo {
        private final Semaphore first = new Semaphore(0);

        private final Semaphore second = new Semaphore(0);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            first.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            first.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            second.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            second.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}

