package org.vc121.light.javainpractice.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sam Lu
 * @date 2022/08/03
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": AAA"));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": BBB"));
        executorService.shutdown();
    }

}
