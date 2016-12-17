package com.github.karina_denisevich.travel_agency.services.security;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Inject
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Inside loadUserByUsername(" + email + ")");
        User user = userService.getByEmailWithRole(email);

        Collection<Role> list = new ArrayList<>();
        list.add(user.getRole());
        Collection<SimpleGrantedAuthority> grantedAuthorities = list.stream().map(authority ->
                new SimpleGrantedAuthority(authority.getType().toString())).collect(Collectors.toCollection(ArrayList::new));

        return new CustomUser(user.getId(), user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}