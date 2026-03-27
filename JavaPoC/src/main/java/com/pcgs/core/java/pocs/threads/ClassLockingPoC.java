package com.pcgs.core.java.pocs.threads;

public class ClassLockingPoC {

    private static int count = 0;

    public static void increment() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is incrementing");
        synchronized (ClassLockingPoC.class) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = ClassLockingPoC::increment;
        Runnable r2 = ClassLockingPoC::increment;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();

        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + count);
    }
}
