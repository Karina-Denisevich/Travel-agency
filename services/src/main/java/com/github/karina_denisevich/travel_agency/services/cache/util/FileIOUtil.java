package com.github.karina_denisevich.travel_agency.services.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileIOUtil<T extends Serializable> {
    private static final Logger logger = LoggerFactory.getLogger(FileIOUtil.class);

    public void write(T object, String fileName) {
        if (object != null) {
            try (ObjectOutput objectOutputStream = new ObjectOutputStream
                    (new BufferedOutputStream(new FileOutputStream(fileName, false)))) {
                objectOutputStream.writeObject(object);
                logger.info(String.format("Object is written in file : %s", object));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T read(String fileName) {
        T object = null;
        try (ObjectInput objectInputStream = new ObjectInputStream
                (new BufferedInputStream(new FileInputStream(fileName)))) {
            object = (T) objectInputStream.readObject();
            logger.info(String.format("Object is read from file : %s", object));
        } catch (FileNotFoundException e) {
            //If file does not exists. New file will created
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
