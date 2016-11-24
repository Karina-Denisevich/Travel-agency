package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public interface DtoToEntity<S, T> extends Converter<S, T> {

    @Override
    T convert(S source);

    default List<T> convert(List<S> sources) {
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
