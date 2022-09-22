package com.msb;

import org.apache.log4j.Logger;

/**
 * @author HuanyuLee
 * @date 2022/3/9
 * @description
 */
public class TestLog4j {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestLog4j.class);
        logger.fatal("fatal message");
        logger.error("error message");
        logger.warn("warn message");
        logger.info("info message");
        logger.debug("debug message");

        // 打印异常信息
        try {
            int i = 1/0;
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
