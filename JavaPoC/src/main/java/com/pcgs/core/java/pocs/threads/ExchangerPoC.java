package com.pcgs.core.java.pocs.threads;

import java.time.Duration;
import java.util.concurrent.Exchanger;

public class ExchangerPoC {
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                String data = "Data from Thread A";
                System.out.println("Thread A sends: " + data);

                String received = exchanger.exchange(data);

                System.out.println("Thread A received: " + received);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                String data = "Data from Thread B";
                System.out.println("Thread B sends: " + data);

                Thread.sleep(Duration.ofSeconds(20).toMillis());
                String received = exchanger.exchange(data);

                System.out.println("Thread B received: " + received);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}