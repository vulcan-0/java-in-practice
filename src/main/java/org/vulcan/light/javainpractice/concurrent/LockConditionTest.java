package org.vulcan.light.javainpractice.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sam Lu
 * @date 2022/5/19
 */
public class LockConditionTest {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new MyThread(condition).start();

        lock.lock();
        try {
            System.out.println("test await");
            condition.await();
            System.out.println("test wake up");
        } finally {
            lock.unlock();
        }
    }

    static class MyThread extends Thread {

        private Condition condition;

        public MyThread(Condition condition) {
            this.condition = condition;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("test signal");
            lock.lock();
            try {
                condition.signal();
            } finally {
                lock.unlock();
            }
            System.out.println("test signal finish");
        }

    }

}
