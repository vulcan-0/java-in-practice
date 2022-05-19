package org.vulcan.light.javainpractice.concurrent;

/**
 * @author Sam Lu
 * @date 2022/5/19
 */
public class WaitAndNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotifyTest test = new WaitAndNotifyTest();
        new MyThread(test).start();

        synchronized (test) {
            System.out.println("test wait");
            test.wait();
            System.out.println("test wake up");
        }
    }

    static class MyThread extends Thread {

        private WaitAndNotifyTest test;

        public MyThread(WaitAndNotifyTest test) {
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
