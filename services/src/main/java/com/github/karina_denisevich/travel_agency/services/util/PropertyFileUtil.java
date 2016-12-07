package com.github.karina_denisevich.travel_agency.services.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

public class PropertyFileUtil {

    public String getKey(String value, String... fileNames) {
        for (String fileName : fileNames) {
            Properties prop = new Properties();
            try (InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
                prop.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String propValue = prop.getProperty(key);
                if (Objects.equals(value, propValue)) {
                    return key;
                }
            }
        }
        return null;
    }

    public void deleteByKey(String key, String... fileNames) {
        for (String fileName : fileNames) {
            try {
                PropertiesConfiguration pc = new PropertiesConfiguration(new File(fileName));
                pc.clearProperty(key);
                pc.save();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }

        }
    }
}
