package com.github.karina_denisevich.travel_agency.daoxml.util;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoxml.exception.XmlFileNotFoundException;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFileIOUtils<T> {

    private final Class<T> genericType;
    private XStream xstream;
    private File file;
    private final String fileName;
    private final String rootName;

    public XmlFileIOUtils(String fileName, Class<T> genericType) throws IOException {
        this.fileName = fileName;
        this.genericType = genericType;
        this.rootName = new DbTableAnalyzer().getDbTableName(genericType);

        initialize();
    }

    private void initialize() throws IOException {
        xstream = new XStream();
        xstream.alias(rootName, genericType);

        file = new File(fileName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    public void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new XmlFileNotFoundException("Some problems with creating xml file.");
        }
    }
}
