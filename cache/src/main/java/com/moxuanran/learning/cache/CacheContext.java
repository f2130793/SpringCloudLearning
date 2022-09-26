package com.moxuanran.learning.cache;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 缓存上下文
 *
 * @author wutao
 * @date 2020/4/13 16:32
 */
public class CacheContext {

    private static ThreadLocal<Set<String>> cacheDowngradeThreadLocal = ThreadLocal.withInitial(Sets::newHashSet);

    public static void cacheDowngrade(String... cacheName) {
        cacheDowngradeThreadLocal.get().addAll(Sets.newHashSet(cacheName));
    }

    public static void removeCacheDowngrade() {
        cacheDowngradeThreadLocal.remove();
    }

    public static Set<String> getCacheDowngradeList() {
        return cacheDowngradeThreadLocal.get();
    }

}
