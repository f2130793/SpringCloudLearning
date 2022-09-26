package com.moxuanran.learning.cache.aspect;

import com.moxuanran.learning.cache.CacheBuilder;
import com.moxuanran.learning.cache.ICache;
import com.moxuanran.learning.cache.anno.Cached;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.support.CacheAnnotationParser;
import com.moxuanran.learning.cache.support.KeyExpressionParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 方法级缓存切面
 *
 * @author wutao
 * @date 2020/4/13 11:10
 */
@Aspect
@Component
@Slf4j
public class CachedAspect {

    @Around(value = "@annotation(com.moxuanran.learning.cache.anno.Cached)")
    public Object cached(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Cached cached = signature.getMethod().getAnnotation(Cached.class);
        // 注解参数转换为配置信息
        PartialCacheConfig<?, ?> cacheConfig = CacheAnnotationParser.parseCached(cached);
        // cache如果已经存在则直接返回cache，如果不存在创建cache后返回
        ICache<Object, Object> cache = CacheBuilder.createCacheBuilder()
                .setName(cacheConfig.getName())
                .setCacheType(cacheConfig.getCacheType())
                .setDefaultCacheLimit(cacheConfig.getDefaultCacheLimit())
                .setExpireAfterWrite(cacheConfig.getExpireAfterWrite())
                .setLocalCacheLimit(cacheConfig.getLocalCacheLimit())
                .setLocalExpireAfterWrite(cacheConfig.getLocalExpireAfterWrite())
                .build();
        // 解析缓存key
        String key = KeyExpressionParser.parse(cached.key(), signature, point.getArgs());
        return cache.computeIfAbsent(key, o -> {
            try {
                return point.proceed();
            } catch (Throwable t) {
                log.error("缓存加载异常，cache name：{}，cache key：{}", cacheConfig.getName(), key, t);
                throw new RuntimeException(t);
            }
        });
    }

}
