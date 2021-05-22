package com.lrg.thread.lock;

import java.util.concurrent.TimeUnit;

public class BooleanLockTest {
    private static final BooleanLock lock = new BooleanLock();

    public static void test() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " enter...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " exit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(BooleanLockTest::test);
        thread1.start();

        Thread thread2 = new Thread(BooleanLockTest::test);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
