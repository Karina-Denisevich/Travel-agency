package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.Tour2CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Tour2CategoryDaoImpl implements Tour2CategoryDao{

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertBatch(Tour tour) {

        final String sql = "INSERT INTO tour_2_category (tour_id, category_id)" +
                " VALUES (?, ?)";
        List<Category> categories = tour.getCategoryList();

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Category category = categories.get(i);
                ps.setLong(1, tour.getId());
                ps.setLong(2, category.getId());
            }

            public int getBatchSize() {
                return categories.size();
            }
        });
    }
}
