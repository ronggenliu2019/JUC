package com.lrg.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

//TODO practice线程间通信。
//TODO 显示锁都是通过wait，wait(milliseconds),notify, notifyAll实现的。
public class BooleanLock implements Lock {
    private volatile boolean locked = false;
    private final ArrayList<Thread> waitThreads = new ArrayList<>();

    private Thread currentThread = null;

    public void lock() throws InterruptedException {
        synchronized (this) {
            Thread currentThread = Thread.currentThread();
            while (locked) {
                System.out.println(currentThread.getName() + " wait...");
                if (waitThreads.contains(currentThread)) {
                    waitThreads.add(currentThread);
                }
                wait();
            }
            locked = true;
            this.currentThread = currentThread;
            waitThreads.remove(currentThread);
            System.out.println(currentThread.getName() + " locked...");
        }
    }

    public void unlock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                locked = false;
                notifyAll();
                System.out.println(Thread.currentThread().getName() + " unlocked...");
            }
        }
    }

    @Override
    public void lock(int milliseconds) throws InterruptedException, TimeoutException {
        synchronized (this) {
            //TODO synchronized同步模块，虽然是串行化的，需要等待Processor的monitor lock，这个切换是几毫秒级别的，很短。
            //TODO 所以可以忽略拿到monitor lock之前的时间。
            System.out.println(Thread.currentThread().getName() + " got monitor lock...");
            if (milliseconds <= 0) {
                //TODO Synchronized同步块是可以嵌套使用的。
                lock();
            } else {
                long remainingTime = milliseconds;
                long endTime = System.currentTimeMillis() + milliseconds;
                Thread currentThread = Thread.currentThread();
                while (locked) {
                    if (remainingTime <= 0) {
                        throw new TimeoutException(Thread.currentThread().getName() + " lock timeout...");
                    }
                    if(!waitThreads.contains(currentThread)) {
                        waitThreads.add(currentThread);
                    }
                    //TODO 这里使得不仅可以响应unlock的notifyAll， 同时timeout后可以重新计算remaining time后判断是否抛出Timeout。
                    wait(remainingTime);
                    remainingTime = endTime - System.currentTimeMillis();
                }
                locked = true;
                waitThreads.remove(currentThread);
                this.currentThread = currentThread;
            }
        }
    }

    public boolean tryLock() {
        synchronized (this) {
            Thread currentThread = Thread.currentThread();
            if (!locked) {
                this.currentThread = currentThread;
                locked = true;
                return true;
            }
            return false;
        }
    }

    public ArrayList<Thread> getWaitThreads() {
        return waitThreads;
    }

    public Thread getCurrentThread() {
        return currentThread;
    }
}
