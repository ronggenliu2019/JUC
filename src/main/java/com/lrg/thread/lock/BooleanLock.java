package com.lrg.thread.lock;

import java.util.ArrayList;

public class BooleanLock implements Lock {
    private volatile boolean locked = false;
    private final ArrayList<Thread> lockedThreads = new ArrayList<Thread>();

    public void lock() throws InterruptedException {
        synchronized (this) {
            Thread currentThread = Thread.currentThread();
            while (locked) {
                lockedThreads.add(currentThread);
                wait();
            }
            System.out.println(currentThread.getName() + " locked...");
            locked = true;
            lockedThreads.remove(currentThread);
        }
    }

    public void unlock() {
        synchronized (this) {
            locked = false;
            notifyAll();
            System.out.println(Thread.currentThread().getName() + " unlocked...");
        }
    }

    public void tryLock() throws InterruptedException {
        synchronized (this) {

        }
    }

//    @Override
//    public void lock(int millseconds) throws InterruptedException {
//
//    }
}
