package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

import java.util.List;

public interface RoleDao {

    Role get(Long id);

    void insert(Role entity);

    void update(Role entity);

    void delete(Long id);

    List<Role> getAll();
}
