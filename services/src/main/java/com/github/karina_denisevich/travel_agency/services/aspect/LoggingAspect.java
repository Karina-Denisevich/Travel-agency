package com.github.karina_denisevich.travel_agency.services.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..))",
            argNames = "joinPoint")
    public Object logTheMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("Beginning of ").append(joinPoint.getTarget().getClass().getName())
                .append("."); //class name
        logMessage.append(joinPoint.getSignature().getName());//method name
        logMessage.append("(");

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]);
            if (i < args.length - 1) {
                logMessage.append(", ");
            }
        }
        logMessage.append(")");
        logger.info(logMessage.toString());

        Object o = joinPoint.proceed();

        logMessage.append(" returned : ").append(o.toString());
        logger.info(logMessage.toString());

        return o;
    }

    @AfterThrowing(pointcut = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..))",
            throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append(joinPoint.getTarget().getClass().getName()).append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append(" Error : ").append(error.getMessage());

        logger.error(logMessage.toString());
    }
}
