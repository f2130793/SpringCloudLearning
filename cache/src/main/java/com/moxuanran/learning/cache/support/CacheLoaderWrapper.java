package com.moxuanran.learning.cache.support;

import com.moxuanran.learning.cache.ICacheLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @author wutao
 * @date 2020/4/10 20:58
 */
@Slf4j
public class CacheLoaderWrapper {

    public static <T, R> Function<T, R> wrap(Function<T, R> function) {
        return key -> {
            try {
                return function instanceof ICacheLoader ? ((ICacheLoader<T, R>) function).load(key) : function.apply(key);
            } catch (Exception e) {
                log.error("cache load error", e);
                throw new RuntimeException(e);
            }
        };
    }

}
