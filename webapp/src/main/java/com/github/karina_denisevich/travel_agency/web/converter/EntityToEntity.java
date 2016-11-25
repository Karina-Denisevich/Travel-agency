package com.github.karina_denisevich.travel_agency.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityToEntity<S, T> implements Converter<S, T> {

    @Override
    public abstract T convert(S source);

    public List<T> convert(List<S> sources) {
        if (sources == null) {
            return null;
        }

        List<T> dtoList = new ArrayList<>();
        for (S s : sources) {
            dtoList.add(this.convert(s));
        }
        return dtoList;
    }
}
