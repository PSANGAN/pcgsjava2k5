package com.pcgs.core.java.pocs.threads;

import java.time.Duration;

public class WaitNotify {
    public static void main(String[] args) {
        Process process = new Process();
        try {
            Thread t1 = new Thread( () ->{
                try {
                    process.produce();
                } catch (java.lang.InterruptedException e) {
                    // TODO: handle exception
                }
            });
            Thread t2 = new Thread(() ->{
                try {
                    process.consume();
                } catch (java.lang.InterruptedException e) {
                    // TODO: handle exception
                }
            });
            t1.start();
            t2.start();
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class Process {
    public void produce() throws java.lang.InterruptedException {
        synchronized (this) {
            System.out.println("Produce - start");
            wait();
            System.out.println("Produce - end");
        }
    }

    public void consume() throws java.lang.InterruptedException{
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        synchronized (this) {
            System.out.println("consume - start");
            notify();
            System.out.println("consume - end");
        }
    }
}
