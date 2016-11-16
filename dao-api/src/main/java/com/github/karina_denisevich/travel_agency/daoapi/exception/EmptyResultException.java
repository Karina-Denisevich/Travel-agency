package com.github.karina_denisevich.travel_agency.daoapi.exception;

public class EmptyResultException extends RuntimeException {

    public EmptyResultException() {

    }

    public EmptyResultException(String message) {
        super(message);
    }
}
