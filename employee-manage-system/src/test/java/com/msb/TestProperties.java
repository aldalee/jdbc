package com.msb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author HuanyuLee
 * @date 2022/3/9
 * @description
 */
public class TestProperties {
    public static void main(String[] args) {
        final Properties properties = new Properties();
        final InputStream inputStream = TestProperties.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String driver = properties.getProperty("driver");
        System.out.println("driver = " + driver);
    }
}
