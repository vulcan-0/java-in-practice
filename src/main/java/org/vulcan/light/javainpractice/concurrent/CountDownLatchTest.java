package org.vulcan.light.javainpractice.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sam Lu
 * @date 2022/5/16
 */
public class CountDownLatchTest {

    static int count;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            testCountDownLatch();
        }
    }

    private static void testCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                count++;
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count);
    }

}
