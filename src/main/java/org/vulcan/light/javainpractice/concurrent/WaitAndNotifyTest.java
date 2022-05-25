package org.vulcan.light.javainpractice.concurrent;

/**
 * @author Sam Lu
 * @date 2022/5/19
 */
public class WaitAndNotifyTest {

    private static final Object TEST = new Object();

    public static void main(String[] args) throws InterruptedException {
        new MyThread(TEST).start();

        synchronized (TEST) {
            System.out.println("test wait");
            TEST.wait();
            System.out.println("test wake up");
        }
    }

    static class MyThread extends Thread {

        private final Object test;

        public MyThread(Object test) {
            this.test = test;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (test) {
                System.out.println("test notify");
                test.notifyAll();
            }
        }

    }

}
