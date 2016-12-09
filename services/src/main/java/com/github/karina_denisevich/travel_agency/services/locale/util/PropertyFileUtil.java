package com.github.karina_denisevich.travel_agency.services.locale.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

public class PropertyFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyFileUtil.class);
    private static final String templateFileName = "services\\src\\main\\resources\\";

    public String getKeyByValue(String value, String... fileNames) {
        for (String fileName : fileNames) {
            Properties prop = new Properties();
            try (InputStream in = new FileInputStream(fileName)) {
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

    public String getValue(String key, String baseName, String language) {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(getPath(baseName, language))) {
            prop.load(in);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
//        return messageSource.getMessage(id.toString().concat(".").concat(key), null,
//                new Locale(language));
    }

    private String getPath(String baseName, String language) {
        return templateFileName + baseName + '_' + language + ".properties";
    }

    public void deleteByKey(String keyPrefix, String... fileNames) {
        for (String fileName : fileNames) {
            try {
                PropertiesConfiguration pc = new PropertiesConfiguration(new File(fileName));
                pc.clearProperty(pc.getKeys(keyPrefix).next());
                pc.save();
                logger.info("From " + fileName + " deleted property starting from " + keyPrefix);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                logger.error("Some problem with resource bundle " + fileName);
            }
        }
    }

    public void write(String key, String value, String fileName) {
        try {
            PropertiesConfiguration pc = new PropertiesConfiguration(new File(fileName));
            pc.setReloadingStrategy(new FileChangedReloadingStrategy());
            pc.addProperty(key, value);
            pc.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
