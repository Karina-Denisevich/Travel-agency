package com.github.karina_denisevich.travel_agency.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileIOUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileIOUtil.class);

    public static void write(Map<String, Object> map) {
        try (ObjectOutput objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("cacheStorage", false)))) {
            objectOutputStream.writeObject(map);
        } catch (FileNotFoundException e) {
            new File("customCache").mkdirs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ConcurrentHashMap<String, Object> read() {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        try {
            try (ObjectInput objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("cacheStorage")))) {
                map = (ConcurrentHashMap<String, Object>) objectInputStream.readObject();
                System.out.println(")))))))))))map = " + map);
            }
        } catch (FileNotFoundException e) {
            new File("customCache").mkdirs();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
}
