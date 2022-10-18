package com.moxuanran.learning.cache.config;


/**
 * 缓存全局配置
 *
 * @author moxuanran 
 * 
 */
public class GlobalCacheConfig {

    public static Integer getExpireAfterWrite() {
        return SysConfig.getInstance().getExpireAfterWrite();
    }

    public static Integer getLocalExpireAfterWrite() {
        return SysConfig.getInstance().getLocalExpireAfterWrite();
    }

    public static String getKeyPrefix() {
        return SysConfig.getInstance().getKeyPrefix();
    }

    public static Integer getExpireRandom() {
        return SysConfig.getInstance().getExpireRandom();
    }

    public static Integer getLocalCacheLimit() {
        return SysConfig.getInstance().getLocalCacheLimit();
    }

    public static Integer getDowngradeExpireAfterWrite() {
        return SysConfig.getInstance().getDowngradeExpireAfterWrite();
    }

    public static Integer getLockTimeoutExpire() {
        return 20000;
    }

}
