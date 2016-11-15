package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.RoleDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoXmlImpl extends GenericDaoXmlImpl<Role, Long> implements RoleDao {

    @Override
    public Role getByType(Role.RoleEnum roleEnum) {
        List<Role> roleList = readCollection();

        for (Role role : roleList) {
            if (role.getType().equals(roleEnum)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public void update(Role entity) {
//        List<Role> roleList = readCollection();
//
//        for (Role role : roleList) {
//            if (role.getId().equals(entity.getId())) {
//
//            }
//        }
    }

    @Override
    public void delete(Long id) {
        List<Role> roleList = readCollection();

        // TODO: don't iterate whole collection
        for (Role role : roleList) {
            if (role.getId().equals(id)) {
                roleList.remove(role);
                break;
            }
        }
        writeCollection(roleList);
    }

    @Override
    public List<Role> getAll() {
        return null;
    }
}
