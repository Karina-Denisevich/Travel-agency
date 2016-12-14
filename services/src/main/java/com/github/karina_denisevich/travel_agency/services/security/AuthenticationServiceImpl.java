package com.github.karina_denisevich.travel_agency.services.security;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Inject
    private UserService userService;

    @Override
    @Cacheable(value = "userInfo", key = "#email + '_' + #password")
    public boolean validateUserPassword(String email, String password) {
        boolean isValid = false;
        User user;
        try {
            user = userService.getByEmail(email);
        } catch (EmptyResultException ex) {
            logger.info("User with email " + email + " is not found. Returned " + isValid);
            return isValid;
        }
        isValid = new BCryptPasswordEncoder().matches(password, user.getPassword());
        logger.info("User with email " + email + " is found. Returned " + isValid);
        return isValid;
    }
}
