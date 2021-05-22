package com.lrg.thread.lock;

public interface Lock {
    public void lock() throws InterruptedException;

    public void unlock() throws InterruptedException;

//    public void lock(int millseconds) throws InterruptedException;
    public void tryLock() throws InterruptedException;
}
