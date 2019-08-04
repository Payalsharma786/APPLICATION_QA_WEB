package com.globallogic.test.utilities;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    Logger logger = Logger.getLogger(PropertiesUtils.class);
    static {
        try {
            PropertiesUtils object = new PropertiesUtils();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Properties properties;
    FileReader reader;

    public PropertiesUtils() throws IOException {
        properties = new Properties();
        reader = new FileReader("src\\main\\java\\application.properties");
        logger.debug("Properties are being loaded");
        properties.load(reader);
        logger.debug("Properties are loaded");
    }

    public static String getProperty(String propertyKey) {
        String propertyValue = null;
        propertyValue = properties.getProperty(propertyKey);
        return propertyValue;
    }
}
