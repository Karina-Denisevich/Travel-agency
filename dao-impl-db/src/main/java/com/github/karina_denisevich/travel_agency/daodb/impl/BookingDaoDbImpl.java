package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daodb.mapper.*;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.BookingUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BookingDaoDbImpl extends GenericDaoDbImpl<Booking, Long> implements BookingDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tourTableName;
    private final String userTableName;
    private final String roleTableName;

    public BookingDaoDbImpl() {
        super(new BookingUnmapper());
        this.tourTableName = new DbTableAnalyzer().getDbTableName(Tour.class);
        this.userTableName = new DbTableAnalyzer().getDbTableName(User.class);
        this.roleTableName = new DbTableAnalyzer().getDbTableName(Role.class);
    }

    @Override
    public int deleteByUserId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE user_id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteByTourId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE tour_id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        final String sql = "SELECT * FROM " + tableName + " b "
                + "LEFT JOIN " + tourTableName + " t ON b.tour_id=t.id "
                + "WHERE b.user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{userId},
                new BookingWithToursMapper(new TourMapper()));
    }

    @Override
    public Booking getByIdWithUser(Long id) {
        String sql = "SELECT * FROM " + tableName + " b "
                + "LEFT JOIN " + userTableName + " u ON b.user_id=u.id "
                + "LEFT JOIN " + roleTableName + " r ON u.role_id=r.id "
                + "WHERE b.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id},
                    new BookingWithUsersMapper(new UserWithRoleMapper(new RoleMapper())));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with id = " + id);
        }
    }
}
