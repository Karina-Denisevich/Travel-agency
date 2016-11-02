package com.github.karina_denisevich.travel_agency.datamodel;

import com.github.karina_denisevich.travel_agency.annotation.DbTable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DbTable(name = "user")
public class User extends AbstractModel {

    private String email;
    private String password;
    private Role role;
    private UserDetails userDetails;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
