package com.github.karina_denisevich.travel_agency.web.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDto extends AbstractDto {

    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Length(min = 5, message = "Password length should be more than 4")
    private String password;
    private RoleDto roleDto;

    public RoleDto getRole() {
        return roleDto;
    }

    public void setRole(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleDto=" + roleDto +
                '}';
    }
}
