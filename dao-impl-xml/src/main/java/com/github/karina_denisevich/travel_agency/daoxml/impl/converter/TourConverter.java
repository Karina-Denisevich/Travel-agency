package com.github.karina_denisevich.travel_agency.daoxml.impl.converter;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.lang.reflect.Field;
import java.util.Objects;

public class TourConverter implements GenericConverter<Tour> {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Tour tour = (Tour) source;

        writer.startNode("id");
        writer.setValue(tour.getId().toString());
        writer.endNode();

        for (Field field : tour.getClass().getDeclaredFields()) {
            Object value = getValue(field, tour);
            if (value != null && !Objects.equals(field.getName(), "categoryList")) {
                writer.startNode(field.getName());
                writer.setValue(value.toString());
                writer.endNode();
            }
        }
    }

    private Object getValue(Field field, Tour tour) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(tour);
        } catch (IllegalAccessException e) {
            //never happens
        }
        return value;
    }

    @Override
    public boolean canConvert(Class type) {
        return Tour.class.isAssignableFrom(type);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Tour tour = new Tour();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("id".equalsIgnoreCase(nodeName)) {
                tour.setId(Long.valueOf(reader.getValue()));
            } else if ("title".equalsIgnoreCase(nodeName)) {
                tour.setTitle(reader.getValue());
            } else if ("isHot".equalsIgnoreCase(nodeName)) {
                tour.setIsHot(Boolean.valueOf(reader.getValue()));
            } else if ("price".equalsIgnoreCase(nodeName)) {
                tour.setPrice(Double.valueOf(reader.getValue()));
            } else if ("description".equalsIgnoreCase(nodeName)) {
                tour.setDescription(reader.getValue());
            } else if ("photoLink".equalsIgnoreCase(nodeName)) {
                tour.setPhotoLink(reader.getValue());
            }
            reader.moveUp();
        }
        return tour;
    }
}
