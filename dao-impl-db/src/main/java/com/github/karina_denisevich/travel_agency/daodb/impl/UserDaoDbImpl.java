package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daodb.mapper.RoleMapper;
import com.github.karina_denisevich.travel_agency.daodb.mapper.UserWithRoleMapper;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.UserUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoDbImpl extends GenericDaoDbImpl<User, Long> implements UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final String roleTableName;

    public UserDaoDbImpl() {
        super(new UserUnmapper());
        this.roleTableName = new DbTableAnalyzer().getDbTableName(Role.class);
    }

    @Override
    public void insertBatch(List<User> userList) {
        final String sql = "INSERT INTO " + tableName + " (email, password, role_id)" +
                " VALUES (?, ?, ?)";

        try {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User user = userList.get(i);
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.setLong(3, user.getRole().getId());
                }

                public int getBatchSize() {
                    return userList.size();
                }
            });
        } catch (DuplicateKeyException ex) {
            throw new DuplicateEntityException(ex.getCause().getMessage());
        }
    }

    @Override
    public User getByEmail(String email) {
        final String sql = "SELECT * FROM " + tableName + " WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email},
                    new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with email = " + email);
        }
    }

    @Override
    public User getWithRole(Long id) {
        final String sql = "SELECT * FROM " + tableName + " u "
                + "LEFT JOIN " + roleTableName + " r ON u.role_id=r.id "
                + "WHERE u.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id},
                    new UserWithRoleMapper(new RoleMapper()));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with id = " + id);
        }
    }

    @Override
    public List<User> getByRole(Role role) {
        final String sql = "SELECT * FROM " + tableName + " WHERE role_id = ?";

        try {
            return jdbcTemplate.query(sql, new Object[]{role.getId()},
                    new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getByEmailWithRole(String email) {
        final String sql = "SELECT * FROM " + tableName + " u "
                + "LEFT JOIN " + roleTableName + " r ON u.role_id=r.id "
                + "WHERE u.email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email},
                    new UserWithRoleMapper(new RoleMapper()));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with email = " + email);
        }
    }
}
