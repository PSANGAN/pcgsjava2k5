package com.pcgs.core.java.pocs.threads;

import java.time.Duration;

public class threads {
    public static void main(String[] args) {

//        Thread t1 = new Thread( () -> {
//            for (int i = 0; i < 25; i++) {
//                System.out.println("Thread 1: " + i);
//            }
//        });
//        t1.start();
//
//        Thread t2 = new Thread(new RunnableWorker());
//        t2.start();
//
//      Runnable r = () -> {
//        for (int i = 25; i < 50; i++) {
//            System.out.println("Thread 3: " + i);
//        }
//      };
//      Thread t3 = new Thread(r);
//      t3.start();


        Thread t1 = new workerThread();
        t1.start();
    }

}

class RunnableWorker implements Runnable {
    @Override
    public void run() {
        for (int i = 50; i < 100; i++) {
            System.out.println("Thread 2: " + i);
        }
    }
}

class workerThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 50; i < 100; i++) {
            try {
                Thread.sleep(Duration.ofSeconds(10).toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                break; // Exit the loop if interrupted
            }
            System.out.println("Thread 2: " + i);
        }
    }
}


