package org.vulcan.light.javainpractice.concurrent;

import org.vulcan.light.javainpractice.util.ThreadUtil;

import java.util.concurrent.CompletableFuture;

/**
 * @author Sam Lu
 * @date 2022/5/25
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::run);
        cf.thenAccept((result) -> {
            System.out.println("result: " + result);
        });
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        ThreadUtil.sleep(2000);
    }

    static Double run() {
        ThreadUtil.sleep(1000);
        System.out.println("run completed");
        return 1.0d;
    }

}
