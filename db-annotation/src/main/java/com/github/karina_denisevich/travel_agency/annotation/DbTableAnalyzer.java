package com.github.karina_denisevich.travel_agency.annotation;

import com.google.common.base.CaseFormat;

import java.lang.annotation.Annotation;

public class DbTableAnalyzer {

    public String getDbTableName(Class<?> clazz){

        String tableName;
        if (clazz.isAnnotationPresent(DbTable.class)) {
            Annotation annotation = clazz.getAnnotation(DbTable.class);
            DbTable dbTable = (DbTable) annotation;
            if (!dbTable.name().isEmpty()) {
                tableName = dbTable.name();
            } else {
                tableName = getTableName(clazz.getSimpleName());
            }
        } else {
            tableName = getTableName(clazz.getSimpleName());
        }

        return tableName;
    }

    private String getTableName(String className) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, className);
    }
}
