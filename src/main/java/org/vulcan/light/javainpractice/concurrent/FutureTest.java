package org.vulcan.light.javainpractice.concurrent;

import org.vulcan.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Sam Lu
 * @date 2022/5/23
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = ThreadUtil.singleThreadPool();
        Future<String> future = executor.submit(new Task());
        System.out.println(future.get());
        executor.shutdown();
    }

    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "success";
        }

    }

}
