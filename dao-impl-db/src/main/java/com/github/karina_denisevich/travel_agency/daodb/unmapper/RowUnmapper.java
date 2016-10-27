package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import java.io.Serializable;
import java.util.Map;

public interface RowUnmapper<T> {

    Map<String, Object> mapColumns(T t);
}
