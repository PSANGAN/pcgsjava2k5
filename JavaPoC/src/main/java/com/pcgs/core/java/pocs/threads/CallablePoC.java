package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallablePoC {

    public static void main(String[] args)  {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futureList.add(executor.submit(new TaskForce(i)));
        }

        System.out.println("Main thread exiting");
        futureList.stream().forEach( item -> {
            try{
                System.out.println(item.get());
            }
            catch(InterruptedException | ExecutionException e){
                System.out.println(e.getMessage());
            }
        } );

        executor.shutdown();
    }
}

class TaskForce implements Callable<Integer> {
    private int id;
    public TaskForce(int id){
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
       Thread.sleep(Duration.ofSeconds(2).toMillis());
        return id * 2;
    }
}