package org.vulcan.light.javainpractice.concurrent;

import org.vulcan.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sam Lu
 * @date 2022/5/16
 */
public class CountDownLatchTest {

    static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        for (int i = 0; i < n; i++) {
            testCountDownLatch();
        }
    }

    private static void testCountDownLatch() throws InterruptedException {
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(10);
        int n = 100;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            executorService.execute(() -> {
                count.incrementAndGet();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count.get());
        executorService.shutdown();
    }

}
