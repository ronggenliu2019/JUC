package com.lrg.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BooleanLockTest {
    private static final BooleanLock lock = new BooleanLock();

    public static void test() {
        try {
            lock.lock(3_000);
            System.out.println(Thread.currentThread().getName() + " enter...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " exit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void testTryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter tryLock...");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " exit tryLock...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to tryLock...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(BooleanLockTest::test);
        thread1.start();

        Thread thread2 = new Thread(BooleanLockTest::test);
        thread2.start();

        Thread thread3 = new Thread(BooleanLockTest::test);
        thread3.start();

        Thread thread4 = new Thread(BooleanLockTest::testTryLock);
        thread4.start();

        Thread thread5 = new Thread(BooleanLockTest::testTryLock);
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }
}
