package com.pcgs.core.java.pocs.threads;

import java.time.Duration;

public class VolatilePoC {

    public static void main(String[] args) throws  java.lang.InterruptedException{
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        Thread.sleep(Duration.ofSeconds(15).toMillis());
        worker.stop();
        t1.join();
        System.out.println("Main thread exiting");
    }
}

class Worker implements Runnable {
    private volatile  boolean running = true;

    public void run() {
        while (running) {
           System.out.println("Running");
        }
        System.out.println("Stopped");
    }

    public void stop() {
        running = false;
    }
}
