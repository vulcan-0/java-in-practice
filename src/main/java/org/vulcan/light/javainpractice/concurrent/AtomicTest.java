package org.vulcan.light.javainpractice.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Sam Lu
 * @date 2022/5/10
 */
public class AtomicTest {

    private static int count;
    private static AtomicInteger atoMicCount = new AtomicInteger();
    private static AtomicStampedReference<Integer> stampedCount = new AtomicStampedReference<Integer>(0, 0);
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        run1(10000);
        System.out.println("count: " + atoMicCount.intValue() + ", use time: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        run2(10000);
        System.out.println("count: " + longAdder.intValue() + ", use time: " + (System.currentTimeMillis() - time));

        int line = 10;
        for (int i = 0; i < line; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println("count: " + count++);
                    int cc = atoMicCount.getAndIncrement();
                    System.out.println("atoMicCount: " + cc);
                    boolean flag = stampedCount.compareAndSet(cc, cc + 1, cc, cc + 1);
                    System.out.println(flag);
                    longAdder.increment();
                    System.out.println(longAdder.intValue());
                }
            }.start();
        }
    }

    private static void run1(int line) {
        for (int i = 0; i < line; i++) {
            new Thread() {
                @Override
                public void run() {
                    atoMicCount.incrementAndGet();
                    //System.out.println(atoMicCount.incrementAndGet());
                }
            }.start();
        }
    }

    private static void run2(int line) {
        for (int i = 0; i < line; i++) {
            new Thread() {
                @Override
                public void run() {
                    longAdder.increment();
                    //System.out.println(longAdder.intValue());
                }
            }.start();
        }
    }

}
