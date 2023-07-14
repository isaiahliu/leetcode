package p12xx;

import java.util.concurrent.Semaphore;

class Problem1226 {
    public static void main(String[] args) {

    }

    public DiningPhilosophers test() {
        return new DiningPhilosophers();
    }

    class DiningPhilosophers {
        private final Semaphore[] semaphores = {
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1)
        };

        private final int[][] requires = {{0, 1}, {2, 1}, {2, 3}, {3, 4}, {0, 4}};

        public DiningPhilosophers() {
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int[] require = requires[philosopher];

            semaphores[require[0]].acquire();
            semaphores[require[1]].acquire();

            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putLeftFork.run();
            putRightFork.run();

            semaphores[require[1]].release();
            semaphores[require[0]].release();
        }
    }
}

