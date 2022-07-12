package org.vc121.light.javainpractice.concurrent;

import org.vc121.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * @author Sam Lu
 * @date 2022/5/16
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        /*
         * Create CountDownLatch with 3 parties, when all 3 parties
         * will reach common barrier point CyclicBarrierFinishEvent will be
         * triggered
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierFinishEvent());

        RunnableTask runnableTask1 = new RunnableTask(cyclicBarrier, 1000);
        RunnableTask runnableTask2 = new RunnableTask(cyclicBarrier, 2000);
        RunnableTask runnableTask3 = new RunnableTask(cyclicBarrier, 3000);

        // Create and start 3 threads
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(3);
        executorService.execute(runnableTask1);
        executorService.execute(runnableTask2);
        executorService.execute(runnableTask3);
        executorService.shutdown();

        /*
         * We are reusing cyclic barrier using below threads
         */
        executorService = ThreadUtil.newFixedThreadPool(3);
        RunnableTask runnableTask4 = new RunnableTask(cyclicBarrier, 4000);
        RunnableTask runnableTask5 = new RunnableTask(cyclicBarrier, 5000);
        RunnableTask runnableTask6 = new RunnableTask(cyclicBarrier, 6000);

        // Create and start 3 more threads
        executorService.execute(runnableTask4);
        executorService.execute(runnableTask5);
        executorService.execute(runnableTask6);
        executorService.shutdown();
    }

    static class RunnableTask implements Runnable {

        CyclicBarrier cyclicBarrier;
        long sleepTime;

        RunnableTask(CyclicBarrier cyclicBarrier, long sleepTime) {
            this.cyclicBarrier = cyclicBarrier;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() +
                        " is waiting for " + (cyclicBarrier.getParties() - cyclicBarrier.getNumberWaiting() - 1) +
                        " other threads to reach common barrier point");
                /*
                 * when 3 parties will call await() method (i.e. common barrier point)
                 * CyclicBarrierEvent will be triggered and all waiting threads will be released.
                 */
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("As " + cyclicBarrier.getParties() + " threads have reached common barrier point "
                    + Thread.currentThread().getName() +
                    " has been released");
        }

    }

    static class CyclicBarrierFinishEvent implements Runnable {

        @Override
        public void run() {
            System.out.println("As 3 threads have reached common barrier point"
                    + ", CyclicBarrierFinishEvent has been triggered");
            System.out.println("You can update shared variables if any");
        }

    }

}
