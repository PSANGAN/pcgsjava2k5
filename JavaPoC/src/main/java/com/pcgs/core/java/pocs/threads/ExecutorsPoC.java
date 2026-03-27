package com.pcgs.core.java.pocs.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsPoC {
    public static void main(String[] args) throws java.lang.InterruptedException{

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            executor.submit(new Task(i));
//        }
//        executor.shutdown();

//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            executor.submit(new Task(i));
//        }
//        executor.shutdown();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(new Task(1), 2, 5, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Task(i));
        }
       executor.awaitTermination(1, TimeUnit.HOURS);
       Thread.sleep(TimeUnit.MINUTES.toMillis(15));

    }
}

class Task implements Runnable {
    private int id;

    public Task(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task " + id + " is running by Thread id - " + Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}