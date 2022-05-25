package org.vulcan.light.javainpractice.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Sam Lu
 * @date 2022/5/25
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Long result = calculate(0, 10000);
        long endTime = System.currentTimeMillis();
        System.out.println("Normal sum: " + result + " in " + (endTime - startTime) + " ms.");

        ForkJoinTask<Long> task = new SumTask(0, 5000, 10000);
        startTime = System.currentTimeMillis();
        result = ForkJoinPool.commonPool().invoke(task);
        endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    private static long calculate(int start, int end) {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += (long) i * i;
        }
        return sum;
    }

    static class SumTask extends RecursiveTask<Long> {

        private final Integer start;
        private final Integer end;
        private Integer middle;

        public SumTask(int start, int middle, int end) {
            this.start = start;
            this.middle = middle;
            this.end = end;
        }

        private SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (middle == null) {
                return calculate(start, end);
            }

            SumTask sumTask1 = new SumTask(start, middle);
            SumTask sumTask2 = new SumTask(middle, end);
            invokeAll(sumTask1, sumTask2);
            Long sum1 = sumTask1.join();
            Long sum2 = sumTask2.join();
            return sum1 + sum2;
        }

    }

}
