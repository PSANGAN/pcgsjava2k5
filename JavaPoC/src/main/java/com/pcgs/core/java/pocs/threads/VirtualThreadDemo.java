package com.pcgs.core.java.pocs.threads;

import java.util.concurrent.*;

public class VirtualThreadDemo {

    public static void main(String[] args) throws Exception {

        Thread t = Thread.ofVirtual().start(() -> {
            System.out.println("Hello from virtual thread");
        });
        t.join();
        }
    }
