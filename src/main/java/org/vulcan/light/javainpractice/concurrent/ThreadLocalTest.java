package org.vulcan.light.javainpractice.concurrent;

/**
 * @author Sam Lu
 * @date 2022/5/10
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static ThreadLocal<String> threadLocal2 = new ThreadLocal<String>();
    private static int count;

    public static void main(String[] args) {
        int line = 10;
        for (int i = 0; i <= line; i++) {
            new Thread() {
                @Override
                public void run() {
                    threadLocal.set(count);
                    threadLocal2.set("" + ('a' + ThreadLocalTest.count));
                    ThreadLocalTest.count++;
                    System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
                    System.out.println(Thread.currentThread().getName() + ": " + threadLocal2.get());
                }
            }.start();
        }
    }

}
