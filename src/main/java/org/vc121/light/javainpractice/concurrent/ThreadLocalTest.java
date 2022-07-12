package org.vc121.light.javainpractice.concurrent;

import org.vc121.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author Sam Lu
 * @date 2022/5/10
 */
public class ThreadLocalTest {

    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<Integer>();
    private static final ThreadLocal<String> THREAD_LOCAL_2 = new ThreadLocal<String>();
    private static int count;

    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newFixedThreadPool(10);
        int line = 10;
        for (int i = 0; i <= line; i++) {
            executorService.execute(() -> {
                THREAD_LOCAL.set(count);
                THREAD_LOCAL_2.set("" + ThreadLocalTest.count);
                ThreadLocalTest.count++;
                System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
                System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL_2.get());
            });
        }
        executorService.shutdown();
        THREAD_LOCAL.remove();
        THREAD_LOCAL_2.remove();
    }

}
