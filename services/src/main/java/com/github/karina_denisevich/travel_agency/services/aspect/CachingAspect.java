package com.github.karina_denisevich.travel_agency.services.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
@Aspect
public class CachingAspect {

    private static final Logger logger = LoggerFactory.getLogger(CachingAspect.class);

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around(value = "execution( * com.github.karina_denisevich.travel_agency.services.*.get*(..))",
            argNames = "point")
    public Object cacheMethod(ProceedingJoinPoint point) throws Throwable {

        StringBuilder keyBuff = new StringBuilder();

        keyBuff.append(point.getTarget().getClass().getName());

        keyBuff.append(".").append(point.getSignature().getName());

        keyBuff.append("(");

        for (Object arg : point.getArgs()) {
            keyBuff.append(arg.getClass().getSimpleName()).append("=").append(arg).append(";");
        }
        keyBuff.append(")");
        String key = keyBuff.toString();

        Object result = cache.get(key);
        if (result == null) {
            result = point.proceed();
            cache.put(key, result);
            logger.info("Storing value " + result + " to cache");
        } else {
            logger.info("Result '" + result + "' was found in cache");
        }

        return result;
    }

    @After(value = "execution( * com.github.karina_denisevich.travel_agency.services.*.*(..)) " +
            "&& !execution( * com.github.karina_denisevich.travel_agency.services.*.get*(..))")
    public void deleteFromCacheMethod(JoinPoint point) {

        String targetName = point.getTarget().getClass().getName();
        int deletedCount = 0;
        for (String key : cache.keySet()) {
            if (key.startsWith(targetName)) {
                cache.remove(key);
                deletedCount++;
            }
        }
        logger.info("From " + targetName + " was deleted : " + deletedCount + " objects");
    }
}