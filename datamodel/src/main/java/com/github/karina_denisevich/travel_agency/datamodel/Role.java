package com.github.karina_denisevich.travel_agency.datamodel;

public class Role extends AbstractModel {

    private enum RoleEnum{
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_ANONYMOUS;
    }

    private RoleEnum type;

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
