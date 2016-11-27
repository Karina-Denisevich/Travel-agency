package com.github.karina_denisevich.travel_agency.services;

public interface AuthenticationService {

    boolean validateUserPassword(String email, String password);
}
