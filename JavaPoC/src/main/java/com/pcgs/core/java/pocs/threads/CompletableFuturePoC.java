package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class CompletableFuturePoC {
    public static void main(String[] args) {

        try{
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(20).toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello";
            });

            CompletableFuture.supplyAsync(() -> "Hello")
                    .thenApply(s -> s + " World")
                    .thenAccept(System.out::println);

//           System.out.println(future.get());

            String result = future.join(); // wait and get result
            System.out.println(result);


        }
//        catch(java.lang.InterruptedException | java.util.concurrent.ExecutionException e) {
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
