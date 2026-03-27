package com.pcgs.core.java.pocs.threads;

public class ObjectLockingPoC {
    private int count = 0;

    final Object lockT1 = new Object();
    public void increment() {

        synchronized (lockT1) {
            count++;
        }
    }

    public void decrement() {

        synchronized (lockT1) {
            count--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectLockingPoC objectLockingPoC = new ObjectLockingPoC();
        Thread t1 = new Thread(() -> {
            for(int i=0; i<10; i++)
            {
                objectLockingPoC.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i<10; i++)
            {
                objectLockingPoC.decrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Count: " + objectLockingPoC.count);
    }
}
