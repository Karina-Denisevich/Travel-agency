package com.github.karina_denisevich.travel_agency.services.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
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
    }

    @AfterReturning(pointcut = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append(joinPoint.getTarget().getClass().getName()).append("."); //class name
        logMessage.append(joinPoint.getSignature().getName());//method name

        logMessage.append("; Returned : ").append(result);
        logger.info(logMessage.toString());
    }

    @AfterThrowing(pointcut = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..))",
            throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append(joinPoint.getTarget().getClass().getName()).append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append(" Cause : ").append(error.getMessage());

        logger.error(logMessage.toString());
    }
}
