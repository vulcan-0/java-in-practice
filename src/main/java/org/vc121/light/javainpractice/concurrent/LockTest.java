package org.vc121.light.javainpractice.concurrent;

import org.vc121.light.javainpractice.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.*;

/**
 * @author Sam Lu
 * @date 2022/5/19
 */
public class LockTest {

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final StampedLock stampedLock = new StampedLock();
    private final int[] count2 = new int[10];
    private final int[] count3 = new int[10];
    private int count1;

    public static void main(String[] args) {
        LockTest test = new LockTest();
        Random random = new Random();
        int times = 100;

        long time1 = System.currentTimeMillis();
        ExecutorService executorService1 = ThreadUtil.newFixedThreadPool(10);
        for (int i = 0; i < times; i++) {
            executorService1.execute(() -> {
                test.inc1();
                if (test.count1 == times) {
                    System.out.println("count1 inc finish, use time: " + (System.currentTimeMillis() - time1) + "ms");
                }
            });
        }
        executorService1.shutdown();

        long time2 = System.currentTimeMillis();
        ExecutorService executorService2 = ThreadUtil.newFixedThreadPool(10);
        for (int i = 0; i < times; i++) {
            executorService2.execute(() -> {
                test.inc2(random.nextInt(10));
                if (test.get2() == times) {
                    System.out.println("count2 inc finish, use time: " + (System.currentTimeMillis() - time2) + "ms");
                }
            });
        }
        executorService2.shutdown();

        long time3 = System.currentTimeMillis();
        ExecutorService executorService3 = ThreadUtil.newFixedThreadPool(10);
        for (int i = 0; i < times; i++) {
            executorService3.execute(() -> {
                test.inc3(random.nextInt(10));
                if (test.get3() == times) {
                    System.out.println("count3 inc finish, use time: " + (System.currentTimeMillis() - time3) + "ms");
                }
            });
        }
        executorService3.shutdown();
    }

    public void inc1() {
        reentrantLock.lock();
        try {
            System.out.print("count1: " + count1 + " ");
            count1++;
            System.out.println(count1);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void inc2(int index) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            System.out.print("count2[" + index + "]: " + count2[index] + " ");
            count2[index]++;
            System.out.println(count2[index]);
        } finally {
            writeLock.unlock();
        }
    }

    public int get2() {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            int count = 0;
            for (int i : count2) {
                count += i;
            }
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public void inc3(int index) {
        long stamp = stampedLock.writeLock();
        try {
            System.out.print("count3[" + index + "]: " + count3[index] + " ");
            count3[index]++;
            System.out.println(count3[index]);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public int get3() {
        long stamp = stampedLock.tryOptimisticRead();

        int count = 0;
        for (int i : count3) {
            count += i;
        }
        if (stampedLock.validate(stamp)) {
            return count;
        }

        stamp = stampedLock.readLock();
        try {
            count = 0;
            for (int i : count3) {
                count += i;
            }
            return count;
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

}
