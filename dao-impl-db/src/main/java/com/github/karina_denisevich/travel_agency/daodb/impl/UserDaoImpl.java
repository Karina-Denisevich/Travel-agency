package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;


    @Override
    public User get(Long id) {

        String sql = "SELECT * FROM user WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void insert(User entity) {

        String sql = "INSERT INTO user (email, password, role_id)" +
                " VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{entity.getEmail(),
                entity.getPassword(), entity.getRoleId()});

    }

    @Override
    public void update(User entity) {

        String sql = "UPDATE user " +
                "SET email = ?, password = ?, role_id = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, new Object[]{entity.getEmail(),
                entity.getPassword(), entity.getRoleId()});
    }

    @Override
    public void delete(Long id) {

        String sql = "DELETE FROM user WHERE id = ?";

        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public List<User> getAll() {

        String sql = "SELECT * FROM user";

        List<User> users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(User.class));

        return users;
    }

    @Override
    public User getByEmail(String email) {

        String sql = "SELECT * FROM user WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email},
                new BeanPropertyRowMapper<>(User.class));
    }
}
