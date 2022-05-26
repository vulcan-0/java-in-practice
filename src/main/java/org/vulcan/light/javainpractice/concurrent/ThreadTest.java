package org.vulcan.light.javainpractice.concurrent;

import org.vulcan.light.javainpractice.util.ThreadUtil;

/**
 * @author Sam Lu
 * @date 2022/5/25
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread().getName() + " " + "hhh");

        // 等待线程1、2执行完毕
        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + " " + "yyy");

        Thread thread3 = new MyThread2();
        thread3.start();

        // 主线程基本执行完成了，其他线程可试着抢占
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " " + "zzz");
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            ThreadUtil.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " " + 1);
            System.out.println(Thread.currentThread().getName() + " " + 2);
            System.out.println(Thread.currentThread().getName() + " " + 3);
        }

    }

    static class MyThread2 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + 11);
            System.out.println(Thread.currentThread().getName() + " " + 22);
            System.out.println(Thread.currentThread().getName() + " " + 33);
        }

    }

}
