package com.moxuanran.learning.cache.aspect;

import com.moxuanran.learning.cache.DisableCache;
import com.moxuanran.learning.cache.ICache;
import com.moxuanran.learning.cache.config.SysConfig;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 缓存JDK代理handler
 *
 * @author moxuanran 
 * 
 */
@AllArgsConstructor
public class CacheInvocationHandler implements InvocationHandler {

    private static final String GET_METHOD_NAME = "get";
    private static final String GET_ALL_METHOD_NAME = "getAll";
    private static final String PUT_METHOD_NAME = "put";
    private static final String PUT_ALL_METHOD_NAME = "putAll";
    private static final String COMPUTE_IF_ABSENT_METHOD_NAME = "computeIfAbsent";

    private ICache<Object, Object> cache;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        // 缓存是否打开
        if (!SysConfig.getInstance().getSwitchOpenCache()) {
            DisableCache<Object, Object> disableCache = new DisableCache<>(cache.getPartialCacheConfig().getCacheLoader());
            return method.invoke(disableCache, args);
        }

        // 默认缓存是否打开
        if (SysConfig.getInstance().getSwitchDefaultData()
                && cache.hasDefaultCache()) {
            if (GET_METHOD_NAME.equals(method.getName())
                    || COMPUTE_IF_ABSENT_METHOD_NAME.equals(method.getName())) {
                result = cache.getDefaultCache().getIfPresent(args[0]);
            } else if (GET_ALL_METHOD_NAME.equals(method.getName())) {
                result = cache.getDefaultCache().getAllPresent((Iterable<?>) args[0]);
            }
            return result;
        }

        // 调用被代理对象
        result = method.invoke(cache, args);

        // 放入默认缓存
        if (cache.hasDefaultCache()) {
            switch (method.getName()) {
                case PUT_METHOD_NAME:
                    if (args[1] != null) {
                        cache.getDefaultCache().put(args[0], args[1]);
                    }
                    break;
                case PUT_ALL_METHOD_NAME:
                    if (result != null) {
                        cache.getDefaultCache().putAll((Map<?, ?>) result);
                    }
                    break;
                case COMPUTE_IF_ABSENT_METHOD_NAME:
                    if (result != null) {
                        cache.getDefaultCache().put(args[0], result);
                    }
                    break;
                default:
                    break;
            }
        }

        return result;
    }

}
