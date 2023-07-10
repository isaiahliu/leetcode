package p11xx;

import java.util.concurrent.Semaphore;

class Problem1117 {
    public static void main(String[] args) throws InterruptedException {
        final H2O test = new Problem1117().test();

    }

    public H2O test() {
        return new H2O();
    }

    class H2O {
        private final Semaphore hydrogen = new Semaphore(0);

        private final Semaphore oxygen = new Semaphore(0);

        public H2O() {
            refresh();
        }

        private synchronized void refresh() {
            if (hydrogen.availablePermits() == 0 && oxygen.availablePermits() == 0) {
                hydrogen.release(2);
                oxygen.release();
            }
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogen.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

            refresh();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygen.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();

            refresh();
        }
    }
}

