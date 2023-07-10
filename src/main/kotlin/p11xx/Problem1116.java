package p11xx;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class Problem1116 {
    public static void main(String[] args) {
        final ZeroEvenOdd test = new Problem1116().test();

        final IntConsumer c = System.out::println;

        new Thread(() -> {
            try {
                test.zero(c);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                test.odd(c);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                test.even(c);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public ZeroEvenOdd test() {
        return new ZeroEvenOdd(5);
    }

    class ZeroEvenOdd {
        private final int n;

        private final Semaphore zero = new Semaphore(0);

        private final Semaphore odd = new Semaphore(0);

        private final Semaphore even = new Semaphore(0);

        private int num = 1;

        private String numStr = "01";

        private int index = 0;

        public ZeroEvenOdd(int n) {
            this.n = n;

            zero.release();
        }

        private boolean next(IntConsumer printNumber) {
            if (numStr.isEmpty()) {
                return false;
            }

            printNumber.accept(numStr.charAt(index++) - '0');

            if (index == numStr.length()) {
                index = 0;
                num++;

                if (num > n) {
                    numStr = "";
                } else {
                    numStr = "0" + num;
                }
            }

            if (index < numStr.length()) {
                int next = numStr.charAt(index) - '0';

                if (next == 0) {
                    zero.release();
                } else if (next % 2 == 1) {
                    odd.release();
                } else {
                    even.release();
                }
            } else {
                zero.release();
                odd.release();
                even.release();
            }

            return true;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            do {
                zero.acquire();
            } while (next(printNumber));
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            do {
                even.acquire();
            } while (next(printNumber));
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            do {
                odd.acquire();
            } while (next(printNumber));
        }
    }
}

