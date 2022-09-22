package com.msb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件 jdbc.properties
 * @author HuanyuLee
 * @date 2022/3/9
 */
public class PropertiesUtil {
    private final Properties properties;

    public PropertiesUtil(String path) {
        properties = new Properties();
        final InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
