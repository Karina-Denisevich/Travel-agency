package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;

import java.util.List;

public interface UserDetailsService {

    Long save(UserDetails userDetails);

    void saveAll(List<UserDetails> userDetailses);

    UserDetails get(Long id);

    List<UserDetails> getAll();

    void delete(Long id);
}
