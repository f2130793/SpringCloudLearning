package com.moxuanran.learning.cache.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 系统变量
 *
 * @author moxuanran 
 *  2020/3/24 19:43
 */
@Configuration
@Data
public class SysConfig {

    private static SysConfig instance;

    public static SysConfig getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        SysConfig.instance = this;
    }

    @Value("${switch.default-data:false}")
    private Boolean switchDefaultData;

    @Value("${switch.use-multi-level-cache:false}")
    private Boolean switchUseMultiLevelCache;

    @Value("${cache.expireAfterWrite:1800000}")
    private Integer expireAfterWrite;

    @Value("${cache.localExpireAfterWrite:300000}")
    private Integer localExpireAfterWrite;

    @Value("${cache.keyPrefix:test}")
    private String keyPrefix;

    @Value("${cache.expireRandom:30000}")
    private Integer expireRandom;

    @Value("${cache.localCacheLimit:1000}")
    private Integer localCacheLimit;

    @Value("${cache.downgradeExpireAfterWrite:300000}")
    private Integer downgradeExpireAfterWrite;

    @Value("${switch.open.cache:true}")
    private Boolean switchOpenCache;

}
