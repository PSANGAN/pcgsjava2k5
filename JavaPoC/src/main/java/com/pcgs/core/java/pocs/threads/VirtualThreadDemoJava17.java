package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.concurrent.*;

public class VirtualThreadDemoJava17 {

    public static void main(String[] args) throws Exception {

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 5; i++) {
                int taskId = i;

                executor.submit(() -> {
                    printThreadInfo("START Task " + taskId);

                    try {
                        // Simulate blocking (this will PARK the virtual thread)
                       //  Thread.sleep(2000);

                        synchronized (VirtualThreadDemoJava17.class) {
                            Thread.sleep(Duration.ofSeconds(20).toMillis());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    printThreadInfo("END   Task " + taskId);
                });
            }
        }
    }

    private static void printThreadInfo(String message) {
        Thread t = Thread.currentThread();

//        System.out.println(
//                message +
//                        " | PlatformThread: " + t +
//                        " | Thread ID: " + t.getId()
//        );

        System.out.println(
                message +
                        "\n  Virtual Thread ID : " + t.threadId() +
                        "\n  Virtual Name      : " + t.getName() +
                        "\n  Is Virtual        : " + t.isVirtual() +
                        "\n  Carrier Thread    : " + Thread.currentThread()
                        + "\n"
        );
    }
}
