package org.vulcan.light.javainpractice.concurrent;

import org.vulcan.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Sam Lu
 * @date 2022/5/10
 */
public class AtomicTest {

    private static final AtomicInteger ATO_MIC_COUNT = new AtomicInteger();
    private static final AtomicStampedReference<String> STAMPED_COUNT = new AtomicStampedReference<>("", 0);
    private static final LongAdder LONG_ADDER = new LongAdder();
    private static int count;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        run1();
        System.out.println("ATO_MIC_COUNT: " + ATO_MIC_COUNT.intValue() + ", use time: " + (System.currentTimeMillis() - time));
        ThreadUtil.sleep(1000);
        System.out.println("ATO_MIC_COUNT: " + ATO_MIC_COUNT.intValue());

        time = System.currentTimeMillis();
        run2();
        System.out.println("LONG_ADDER: " + LONG_ADDER.intValue() + ", use time: " + (System.currentTimeMillis() - time));
        ThreadUtil.sleep(1000);
        System.out.println("LONG_ADDER: " + LONG_ADDER.intValue());

        time = System.currentTimeMillis();
        run3();
        System.out.println("count: " + count + ", use time: " + (System.currentTimeMillis() - time));
        ThreadUtil.sleep(1000);
        System.out.println("count: " + count);

        System.out.println();
        int count = ATO_MIC_COUNT.getAndIncrement();
        String str = "" + count;
        System.out.println("ATO_MIC_COUNT-out: " + count);
        STAMPED_COUNT.set(str, count);
        boolean flag1 = STAMPED_COUNT.compareAndSet(str, "" + (count + 1), count, count + 1);
        System.out.println("flag-out: " + flag1);
    }

    private static void run1() {
        int line = 10000;
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(100);
        for (int i = 0; i < line; i++) {
            executorService.execute(ATO_MIC_COUNT::incrementAndGet);
        }
        executorService.shutdown();
    }

    private static void run2() {
        int line = 10000;
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(100);
        for (int i = 0; i < line; i++) {
            executorService.execute(LONG_ADDER::increment);
        }
        executorService.shutdown();
    }

    private static void run3() {
        int line = 10000;
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(100);
        for (int i = 0; i < line; i++) {
            executorService.execute(() -> count++);
        }
        executorService.shutdown();
    }

}
