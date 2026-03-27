package com.pcgs.core.java.pocs.logging;

import com.pcgs.core.java.pocs.logging.subpackage.UtliCala;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClass {

    private  static final Logger logger = LoggerFactory.getLogger(SimpleClass.class);
    public static void doWork(){
        System.out.println("SimpleClass-doWork");
        logger.warn("A WARN MSG!");
    }
}
