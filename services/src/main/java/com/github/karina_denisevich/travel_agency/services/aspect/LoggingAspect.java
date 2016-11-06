package com.github.karina_denisevich.travel_agency.services.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution(* com.github.karina_denisevich.travel_agency.services.*.*(..))"
            , argNames = "joinPoint")
    public Object logTheMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("************I'm here");
        Object o = joinPoint.proceed();
        System.out.println("********I'm after");
        System.out.println("-----Object = " + o);
        return o;
    }
}
