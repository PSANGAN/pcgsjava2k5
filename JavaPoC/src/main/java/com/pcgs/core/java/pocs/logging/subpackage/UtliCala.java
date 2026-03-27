package com.pcgs.core.java.pocs.logging.subpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;

public class UtliCala {
    private  static final Logger logger = LoggerFactory.getLogger(UtliCala.class);
    public static void doWork(){
        System.out.println("UtliCala-doWork");
        logger.info("A INFO MSG!");
    }
}
