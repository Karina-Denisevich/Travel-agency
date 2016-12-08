package com.github.karina_denisevich.travel_agency.services.aspect.util;

import com.github.karina_denisevich.travel_agency.services.locale.CustomLocale;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Objects;

public class CachingUtil {

    public String getKey(ProceedingJoinPoint point, CustomLocale customLocale) {
        StringBuilder keyBuff = new StringBuilder();

        keyBuff.append(point.getTarget().getClass().getName());
        keyBuff.append(".").append(point.getSignature().getName());
        keyBuff.append("(");

        Arrays.stream(point.getArgs()).filter(Objects::nonNull).forEach(arg -> keyBuff
                .append(arg.getClass().getSimpleName()).append("=").append(arg).append(";"));

        keyBuff.append(customLocale.getLanguage());
        keyBuff.append(")");
        return keyBuff.toString();
    }
}
