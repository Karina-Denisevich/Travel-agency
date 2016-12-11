package com.github.karina_denisevich.travel_agency.services.locale.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class PropertyFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyFileUtil.class);
    private static final String templateFileName = "services\\src\\main\\resources\\";
    private static final List<String> supportedLanguages = Arrays.asList("en", "ru", "de");

    public String getKeyByValue(String value, String... fileNames) {
        for (String fileName : fileNames) {
            Properties prop = new Properties();
            try (InputStream in = new FileInputStream(fileName)) {
                prop.load((new InputStreamReader(in, Charset.forName("UTF-8"))));
            } catch (IOException e) {
                logger.error(String.format("Some problem with resource bundle %1s. Cannot find key by value: %s2",
                        fileName, value));
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

    public String getValueByLocale(String key, String baseName, String language) {
        if (!isLanguageSupported(language)) {
            language = supportedLanguages.get(0);
        }

        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(getPath(baseName, language))) {
            prop.load((new InputStreamReader(in, Charset.forName("UTF-8"))));
            String value = prop.getProperty(key);
            return (value == null) ? key : value;
        } catch (IOException e) {
            logger.error(String.format("Cannot find key %s", key));
            return key;
        }
//        return messageSource.getMessage(id.toString().concat(".").concat(key), null,
//                new Locale(language));
    }

    private String getPath(String baseName, String language) {
        return templateFileName + baseName + '_' + language + ".properties";
    }

    public String getValue(String key, String fileName) {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(fileName)) {
            prop.load(in);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    //TODO
    public synchronized void deleteByKey(String keyPrefix, String... fileNames) {
        for (String fileName : fileNames) {
            Properties prop = new Properties();
            try (InputStream in = new FileInputStream(fileName)) {
                prop.load((new InputStreamReader(in, Charset.forName("UTF-8"))));
                prop.keySet().stream().filter(key -> key.toString().startsWith(keyPrefix))
                        .findFirst().ifPresent(prop::remove);
                prop.store(new OutputStreamWriter(
                        new FileOutputStream(fileName), "UTF-8"), null);
                logger.info(String.format("From %1s deleted property starting from %2s", fileName, keyPrefix));
            } catch (IOException e) {
                logger.error(String.format("Some problem with deleting resource bundle %s", fileName));
            }
        }
    }

    public synchronized void write(String key, String value, String language, String fileName) {
        if (!isLanguageSupported(language)) {
            language = supportedLanguages.get(0);
        }
        String translatedValue = new TranslatorUtil().translate(null, language, value);

        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(fileName)) {
            prop.load((new InputStreamReader(in, Charset.forName("UTF-8"))));
            prop.setProperty(key, translatedValue);
            prop.store(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8"), null);
        } catch (IOException e) {
            logger.error(String.format("Some problem with writing into resource bundle %s", fileName));
        }
    }

    private boolean isLanguageSupported(String language) {
        return supportedLanguages.contains(language);
    }
}
