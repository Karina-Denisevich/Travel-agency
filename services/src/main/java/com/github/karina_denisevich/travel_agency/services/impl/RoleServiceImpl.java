package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    RoleDao roleDao;

    @Override
    public Long save(Role user) {
        return null;
    }

    @Override
    public void saveAll(List<Role> roles) {

    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
