package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.mapper.RoleMapper;
import com.github.karina_denisevich.travel_agency.daodb.mapper.UserWithRoleMapper;
import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.UserUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
        super(new UserUnmapper());
    }

//    @Override
//    public void update(User entity) {
//
//        final String sql = "UPDATE user " +
//                "SET email = ?, password = ?, role_id = ? " +
//                "WHERE id = ?";
//
//        jdbcTemplate.update(sql, entity.getEmail(),
//                entity.getPassword(), entity.getRole().getId(), 40L);
//    }

    @Override
    public void insertBatch(List<User> entityList) {

        final String sql = "INSERT INTO user (email, password, role_id)" +
                " VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = entityList.get(i);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setLong(3, user.getRole().getId());
            }

            public int getBatchSize() {
                return entityList.size();
            }
        });
    }

    @Override
    public User getByEmail(String email) {

        final String sql = "SELECT * FROM user WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getWithRole(Long id) {

        final String sql = "SELECT * FROM user u "
                + "LEFT JOIN role r ON u.role_id=r.id "
                + "WHERE u.id = ?";

        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                new UserWithRoleMapper(new RoleMapper()));
    }
}
