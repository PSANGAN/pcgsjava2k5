package com.pcgs.core.java.pocs.logging;

import com.pcgs.core.java.pocs.logging.subpackage.UtliCala;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlforJ {

    private static final Logger logger = LoggerFactory.getLogger(SlforJ.class);

    public static void main(String[] args){
        logger.trace("A TRACE message");
        logger.debug("A DEBUG message");
        logger.info("An INFO message");
        logger.warn("A WARN message");
        logger.error("An ERROR message");

        UtliCala.doWork();
        SimpleClass.doWork();
    }
}
