package com.moxuanran.learning.cache.aspect;

import com.moxuanran.learning.cache.CacheContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 缓存降级切面
 *
 * @author moxuanran 
 * 
 */
@Aspect
@Component
@Slf4j
public class CacheDowngradeAspect {

    @Around(value = "@annotation(com.moxuanran.learning.cache.anno.CacheDowngrade)")
    public Object cached(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        CacheContext.removeCacheDowngrade();
        return proceed;
    }

}
