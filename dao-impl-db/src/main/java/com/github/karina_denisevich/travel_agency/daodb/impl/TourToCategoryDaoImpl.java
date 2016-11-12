package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
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
public class TourToCategoryDaoImpl implements TourToCategoryDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName = "tour_2_category";

    @Override
    public void insertTourWithCategories(Tour tour) {

        final String sql = "INSERT INTO " + tableName + " (tour_id, category_id)" +
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

    @Override
    public void deleteByTourId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE tour_id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE category_id = ?";

        jdbcTemplate.update(sql, id);
    }
}
