package com.github.karina_denisevich.travel_agency.services.cache;

import com.github.karina_denisevich.travel_agency.services.cache.util.CachingUtil;
import com.github.karina_denisevich.travel_agency.services.locale.CustomLocale;
import com.github.karina_denisevich.travel_agency.services.cache.util.FileIOUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class CachingAspect {
    private static final Logger logger = LoggerFactory.getLogger(CachingAspect.class);
    private static final String FILE_NAME = "cacheStorage";

    private static final int MAX_SIZE = 1000;
    private static final int MIN_SIZE = 500;

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    @Inject
    private CustomLocale customLocale;

    @Around(value = "execution( public * com.github.karina_denisevich.travel_agency.services.impl.*.get*(..))",
            argNames = "point")
    public Object cacheMethod(ProceedingJoinPoint point) throws Throwable {
        String key = new CachingUtil().getKey(point, customLocale);

        Object result = cache.get(key);
        if (result == null) {
            result = point.proceed();
            if (result != null) {
                prepareCache();
                cache.put(key, result);
                logger.info(String.format("Storing value %s to cache", result));
            }
        } else {
            logger.info(String.format("Result '%s' was found in cache", result));
        }
        return result;
    }

    private void prepareCache() {
        if (cache.size() >= MAX_SIZE) {
            List<Object> list = Arrays.asList(cache.keySet().toArray());
            cache.keySet().removeAll(list.subList(MIN_SIZE, MAX_SIZE));
        }
    }

    @After("execution( public * com.github.karina_denisevich.travel_agency.services.impl.*.*(..)) " +
            "&& !execution( public * com.github.karina_denisevich.travel_agency.services.impl.*.get*(..))")
    public void deleteFromCacheMethod(JoinPoint point) {
        String targetName = point.getTarget().getClass().getName();
        int deletedCount = 0;
        for (String key : cache.keySet()) {
            if (key.startsWith(targetName)) {
                cache.remove(key);
                deletedCount++;
            }
        }
        logger.info(String.format("From %1s was deleted : %2s objects", targetName, deletedCount));
    }

    @Scheduled(fixedDelay = 20000)
    public void save() {
        FileIOUtil<ConcurrentHashMap<String, Object>> fileIOUtil = new FileIOUtil<>();
        fileIOUtil.write((ConcurrentHashMap<String, Object>) cache, FILE_NAME);
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        FileIOUtil<ConcurrentHashMap<String, Object>> fileIOUtil = new FileIOUtil<>();
        ConcurrentHashMap<String, Object> map = fileIOUtil.read(FILE_NAME);
        if (map != null) {
            cache = map;
        }
    }

    @Scheduled(fixedDelay = 12000)
    public void clearCache() {
        cache.clear();
    }
}
