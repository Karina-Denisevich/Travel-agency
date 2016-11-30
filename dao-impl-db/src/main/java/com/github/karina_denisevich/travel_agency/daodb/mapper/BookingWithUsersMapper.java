package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.daodb.mapper.util.MapperUtil;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingWithUsersMapper implements RowMapper<Booking> {

    private final UserWithRoleMapper userWithRoleMapper;

    public BookingWithUsersMapper(UserWithRoleMapper userWithRoleMapper) {
        this.userWithRoleMapper = userWithRoleMapper;
    }

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(new MapperUtil().getId(rs, "booking"));
        booking.setOrderDate(rs.getDate("order_date"));
        booking.setIsConfirmed(rs.getBoolean("is_confirmed"));

        User user = this.userWithRoleMapper.mapRow(rs, rowNum);
        booking.setUser(user);

        return booking;
    }
}
