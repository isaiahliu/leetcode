package p11xx;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class Problem1195 {
    public static void main(String[] args) {
        new Problem1195().test();
    }

    public void test() {
        new FizzBuzz(15);
    }

    class FizzBuzz {
        private final int n;

        private final Semaphore fizz = new Semaphore(0);

        private final Semaphore buzz = new Semaphore(0);

        private final Semaphore fizzbuzz = new Semaphore(0);

        private final Semaphore number = new Semaphore(0);

        private int current = 0;

        public FizzBuzz(int n) {
            this.n = n;
            next();
        }

        private void next() {
            current++;

            if (current > n) {
                fizzbuzz.release();
                fizz.release();
                buzz.release();
                number.release();
            } else if (current % 15 == 0) {
                fizzbuzz.release();
            } else if (current % 3 == 0) {
                fizz.release();
            } else if (current % 5 == 0) {
                buzz.release();
            } else {
                number.release();
            }
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            while (true) {
                fizz.acquire();

                if (current > n) {
                    break;
                }

                printFizz.run();

                next();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (true) {
                buzz.acquire();

                if (current > n) {
                    break;
                }

                printBuzz.run();

                next();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (true) {
                fizzbuzz.acquire();

                if (current > n) {
                    break;
                }

                printFizzBuzz.run();

                next();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                number.acquire();

                if (current > n) {
                    break;
                }

                printNumber.accept(current);

                next();
            }
        }
    }
}

