package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.concurrent.*;

public class CountDownLatchPoC {
    public static void main(String[] args) throws java.lang.InterruptedException {
//     CountDownLatch countDownLatch = new CountDownLatch(30);
//     ExecutorService executor = Executors.newFixedThreadPool(3);
//     for (int i = 0; i < 30; i++) {
//        executor.submit(new Job(i, countDownLatch));
//     }
//        countDownLatch.await(); // Blocks Main Thread until all jobs are completed
//        executor.shutdown();
//        System.out.println("Main thread exiting");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5,
                () -> System.out.println("All threads have reached the barrier, " +
                        "they completed their tasks in barrier point," +
                        " now they return to their normal flow"));

        for (int i = 0; i < 5; i++) {
            executor.submit(new BarrierJob(i, barrier));
        }
        executor.shutdown();
  }
}

class Job implements Runnable {
    private int id;
    CountDownLatch countDownLatch;
    public Job(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch =countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            System.out.println("Job " + id + " is completed");
            this.countDownLatch.countDown();
        }
        catch (java.lang.InterruptedException ex) {
           System.out.println(ex.getMessage());
        }

    }
}

class BarrierJob implements Runnable {
    private int id;
    private CyclicBarrier barrier;
    public BarrierJob( int id, CyclicBarrier barrier) {
        this.barrier = barrier;
        this.id = id;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            System.out.println("Job " + id + " is completed");
            barrier.await();
        }
        catch (InterruptedException | BrokenBarrierException ex) { System.out.println(ex.getMessage());}
        System.out.println("Each Thread returns to their normal flow");
    }
}