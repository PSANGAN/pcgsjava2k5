package com.pcgs.core.java.pocs.threads;

import java.util.concurrent.locks.*;

public class ReentrantLockPoC {
    private int data;
    private boolean available = false;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (available) {
                notFull.await(); // wait only producers
            }
            data = value;
            available = true;
            notEmpty.signal(); // wake ONLY consumers
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (!available) {
                notEmpty.await(); // wait only consumers
            }
            available = false;
            notFull.signal(); // wake ONLY producers ✅
            return data;
        } finally {
            lock.unlock();
        }
    }
}