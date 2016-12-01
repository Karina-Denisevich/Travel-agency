package com.github.karina_denisevich.travel_agency.services.aspect.util;

import com.sun.javafx.collections.MappingChange;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;

public class CachingUtil {

    public String getKey(ProceedingJoinPoint point) {
        StringBuilder keyBuff = new StringBuilder();

        keyBuff.append(point.getTarget().getClass().getName());
        keyBuff.append(".").append(point.getSignature().getName());
        keyBuff.append("(");

        for (Object arg : point.getArgs()) {
            if (arg != null) {
                keyBuff.append(arg.getClass().getSimpleName()).append("=").append(arg).append(";");
            } else {
                keyBuff.append("null").append(";");
            }
        }
        keyBuff.append(")");
        return keyBuff.toString();
    }
}
