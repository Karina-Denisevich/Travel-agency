package com.github.karina_denisevich.travel_agency.daodb.mapper.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MapperUtil {

    public Long getId(ResultSet rs, String tableName) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            if (metaData.getTableName(i + 1).equals(tableName)) {
                return rs.getLong(i + 1);
            }
        }
        return null;
    }
}
