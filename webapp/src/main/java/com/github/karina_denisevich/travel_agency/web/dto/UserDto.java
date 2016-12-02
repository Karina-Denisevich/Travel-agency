package com.github.karina_denisevich.travel_agency.web.dto;

public class UserDto extends AbstractDto {

    private String email;
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
