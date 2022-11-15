package org.vc121.light.javainpractice.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sam Lu
 * @date 2022/08/03
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        singleThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        singleThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ": CCC"));
        singleThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ": DDD"));
        singleThreadExecutor.shutdown();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": CCC"));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": DDD"));
        fixedThreadPool.shutdown();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": CCC"));
        cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": DDD"));
        cachedThreadPool.shutdown();

        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        scheduledExecutorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        scheduledExecutorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": CCC"));
        scheduledExecutorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": DDD"));
        scheduledExecutorService.shutdown();

        ExecutorService workStealingPool = Executors.newWorkStealingPool(3);
        workStealingPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        workStealingPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        workStealingPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": CCC"));
        workStealingPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": DDD"));
        workStealingPool.shutdown();
    }

}
