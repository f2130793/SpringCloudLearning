package com.moxuanran.learning.cache.anno;

import java.lang.annotation.*;

/**
 * @author wutao
 * @date 2020/4/13 17:06
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheDowngrade {

}
