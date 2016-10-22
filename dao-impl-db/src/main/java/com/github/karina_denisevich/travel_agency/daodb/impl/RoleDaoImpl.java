package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public void insert(Role entity) {

    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Role> getAll() {
        return null;
    }
}
