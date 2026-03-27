package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.concurrent.Semaphore;

public class SemaphorePoC {
    public static void main(String[] args) throws java.lang.InterruptedException
    {
        Resource resource = new Resource();
        try
        {
            resource.use();
        }
        catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        Thread.sleep(Duration.ofSeconds(30).toMillis());

    }
}



class Resource {
    private final Semaphore semaphore = new Semaphore(3); // 3 permits

    public void use() throws InterruptedException {
        semaphore.acquire(); // take permit
        try {
            System.out.println(Thread.currentThread().getName() + " using resource");
            Thread.sleep(1000);
        } finally {
            semaphore.release(); // return permit
        }
    }
}