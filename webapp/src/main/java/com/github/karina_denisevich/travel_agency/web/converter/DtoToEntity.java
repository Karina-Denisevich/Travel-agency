package com.github.karina_denisevich.travel_agency.web.converter;

import org.springframework.core.convert.converter.Converter;

public interface DtoToEntity<S, T> extends Converter<S, T> {

    @Override
    T convert(S source);
}
