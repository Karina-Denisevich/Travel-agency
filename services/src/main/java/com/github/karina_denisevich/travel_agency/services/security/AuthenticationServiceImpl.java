package com.github.karina_denisevich.travel_agency.services.security;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.security.AuthenticationService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserService userService;

    @Override
    public boolean validateUserPassword(String email, String password) {
        User user;
        try {
            user = userService.getByEmail(email);
        } catch (EmptyResultException ex) {
            return false;
        }
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }
}
