package com.lrg.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BooleanLockTest {
    private static final BooleanLock lock = new BooleanLock();

    public static void test() {
        try {
            lock.lock(2_000);
            System.out.println(Thread.currentThread().getName() + " enter...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " exit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(BooleanLockTest::test);
        thread1.start();

        Thread thread2 = new Thread(BooleanLockTest::test);
        thread2.start();

        Thread thread3 = new Thread(BooleanLockTest::test);
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
