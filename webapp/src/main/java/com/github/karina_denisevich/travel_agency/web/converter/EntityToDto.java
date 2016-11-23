package com.github.karina_denisevich.travel_agency.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.ArrayList;
import java.util.List;

public interface EntityToDto<S, T> extends Converter<S, T> {

    @Override
    T convert(S source);

    default List<T> convert(List<S> sources) {
        List<T> dtoList = new ArrayList<>();
        for (S s : sources) {
            dtoList.add(this.convert(s));
        }
        return dtoList;
    }
}
