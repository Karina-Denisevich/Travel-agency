package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TourDaoXmlImpl implements TourDao {

    private final String rootName;
    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        // TODO move to the parent class
        // TODO refactoring: use classname instead of hardcoded filename
        xstream = new XStream();
        xstream.alias(rootName, Tour.class);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    public TourDaoXmlImpl() {
        this.rootName = new DbTableAnalyzer().getDbTableName(Tour.class);
    }

    @Override
    public Tour get(Long id) {
        return null;
    }

    @Override
    public Long insert(Tour entity) {
        List<Tour> tourList = readCollection();
        Long id = getNextId(tourList);

        tourList.add(entity);

        entity.setId(id);

        writeCollection(tourList);
        return id;
    }

    @Override
    public List<Tour> getByTitle(String title) {
        return null;
    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Tour> getAll() {
        return null;
    }

    private List<Tour> readCollection() {

        return (List<Tour>) xstream.fromXML(file);
    }

    private void writeCollection(List<Tour> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Tour> tourList) {
        return tourList.isEmpty() ? 1L : tourList.get(
                tourList.size() - 1).getId() + 1;
    }
}
