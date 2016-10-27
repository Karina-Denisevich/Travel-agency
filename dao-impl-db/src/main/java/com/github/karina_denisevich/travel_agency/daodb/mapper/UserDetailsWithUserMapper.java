package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsWithUserMapper implements RowMapper<UserDetails> {

    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(rs.getLong("id"));
        userDetails.setFirstName(rs.getString("first_name"));
        userDetails.setLastName(rs.getString("last_name"));
        userDetails.setDiscount(rs.getDouble("discount"));
        userDetails.setbDate(rs.getDate("bdate"));
        userDetails.setPhone(rs.getString("phone"));
        userDetails.setSkype(rs.getString("skype"));

        return userDetails;
    }
}
