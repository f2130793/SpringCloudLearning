package com.moxuanran.learning.controller;

import com.moxuanran.learning.cache.ICache;
import com.moxuanran.learning.cache.anno.CacheType;
import com.moxuanran.learning.cache.anno.CreateCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moxuanran 
 * 
 */
@RestController
public class TestController {
    @CreateCache(name = "test", cacheType = CacheType.LOCAL, localLimit = 5, defaultLimit = 5)
    private ICache<String, String> cache;

    @GetMapping("/get")
    public String get() {
        return cache.computeIfAbsent("hhh11", key -> "jjjj");
    }
}
