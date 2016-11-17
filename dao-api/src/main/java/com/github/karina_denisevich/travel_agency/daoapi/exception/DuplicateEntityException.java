package com.github.karina_denisevich.travel_agency.daoapi.exception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String message) {
        super(message);
    }
}
