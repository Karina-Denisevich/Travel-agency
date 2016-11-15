package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;

import java.util.List;

@DbTable(name = "role")
public class Role extends AbstractModel {

    public enum RoleEnum {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_ANONYMOUS;
    }

    private RoleEnum type;
    private transient List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public RoleEnum getType() {
        return type;
    }

    public void setType(RoleEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "type=" + type +
                '}';
    }
}
