package com.github.karina_denisevich.travel_agency.web.advice;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<Object> handleException(EmptyResultException ex, HttpServletRequest webRequest) {
        logger.error(getLogMessage(ex, webRequest));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private String getLogMessage(EmptyResultException ex, HttpServletRequest webRequest) {
        return ("Request: " + webRequest.getRequestURI()) +
                " Cause : " + ex.getMessage();
    }
}
