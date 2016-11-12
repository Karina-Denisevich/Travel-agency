package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
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
public class BookingDaoXmlImpl implements BookingDao {

    private final String rootName;
    private final String tourRootName;
    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        // TODO move to the parent class
        // TODO refactoring: use classname instead of hardcoded filename
        xstream = new XStream();
        xstream.alias(rootName, Booking.class);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    public BookingDaoXmlImpl() {
        this.rootName = new DbTableAnalyzer().getDbTableName(Booking.class);
        this.tourRootName = new DbTableAnalyzer().getDbTableName(Tour.class);
    }

    @Override
    public Booking get(Long id) {
        return null;
    }

    @Override
    public Long insert(Booking entity) {
        List<Booking> bookingList = readCollection();
        Long id = getNextId(bookingList);

        bookingList.add(entity);

        entity.setId(id);

        writeCollection(bookingList);
        return id;
    }

    @Override
    public void update(Booking entity) {

    }

    @Override
    public void deleteByUserId(Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByTourId(Long id) {

    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        return null;
    }

    private List<Booking> readCollection() {

        return (List<Booking>) xstream.fromXML(file);
    }

    private void writeCollection(List<Booking> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Booking> bookingList) {
        return bookingList.isEmpty() ? 1L : bookingList.get(
                bookingList.size() - 1).getId() + 1;
    }
}
