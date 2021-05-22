package com.lrg.thread.lock;

import java.util.concurrent.TimeoutException;

public interface Lock {
    void lock() throws InterruptedException;

    void unlock() throws InterruptedException;

    void lock(int milliseconds) throws InterruptedException, TimeoutException;

    void tryLock() throws InterruptedException;
}
