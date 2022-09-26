package com.moxuanran.learning.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.moxuanran.learning.cache.config.GlobalCacheConfig;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.support.CacheLoaderWrapper;
import com.moxuanran.learning.cache.support.LoaderLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Abstract缓存
 *
 * @author wutao
 * @date 2020/4/21 12:56
 */
@Slf4j
public abstract class AbstractCache<K, V> implements ICache<K, V> {

    private Cache<K, V> defaultCache;

    private PartialCacheConfig<K, V> cacheConfig;

    private ConcurrentHashMap<String, LoaderLock<V>> loaderLockMap = new ConcurrentHashMap<>();

    public AbstractCache(PartialCacheConfig<K, V> cacheConfig) {
        this.cacheConfig = cacheConfig;
        if (cacheConfig.getDefaultCacheLimit() != 0) {
            defaultCache = Caffeine.newBuilder().maximumSize(cacheConfig.getDefaultCacheLimit()).build();
        }
    }

    @Override
    public V computeIfAbsent(K key, Function<K, V> loader, long expireAfterWrite) {
        V value = get(key);
        if (value == null) {
            StopWatch stopWatch = StopWatch.createStarted();
            String lockKey = buildLockKey(key);
            LoaderLock<V> loaderLock = loaderLockMap.computeIfAbsent(buildLockKey(key), unusedKey ->
                    new LoaderLock<V>().setSignal(new CountDownLatch(1)).setLoaderThread(Thread.currentThread()));
            Function<K, V> loadAndPut = CacheLoaderWrapper.wrap(unusedKey -> {
                V v = loader.apply(key);
                put(key, v, expireAfterWrite);
                return v;
            });
            if (loaderLock.getLoaderThread() == Thread.currentThread()) {
                try {
                    value = loadAndPut.apply(key);
                    loaderLock.setValue(value);
                } catch (RuntimeException e) {
                    loaderLock.setLoaderException(e);
                } finally {
                    loaderLock.getSignal().countDown();
                    loaderLockMap.remove(lockKey);
                }
            } else {
                try {
                    boolean ok = loaderLock.getSignal().await(GlobalCacheConfig.getLockTimeoutExpire(), TimeUnit.MILLISECONDS);
                    stopWatch.stop();
                    log.info("lock cost: {}", stopWatch.getTime(TimeUnit.MILLISECONDS));
                    if (!ok) {
                        log.warn("lock wait timeout，lockKey: {}", lockKey);
                        return loadAndPut.apply(key);
                    }
                } catch (InterruptedException e) {
                    log.warn("lock wait interrupted，lockKey: {}", lockKey);
                    return loadAndPut.apply(key);
                }
                if (loaderLock.getLoaderException() == null) {
                    value = loaderLock.getValue();
                } else {
                    throw loaderLock.getLoaderException();
                }
            }
        }
        return value;
    }

    @Override
    public PartialCacheConfig<K, V> getPartialCacheConfig() {
        return cacheConfig;
    }

    @Override
    public Cache<K, V> getDefaultCache() {
        return defaultCache;
    }

}
