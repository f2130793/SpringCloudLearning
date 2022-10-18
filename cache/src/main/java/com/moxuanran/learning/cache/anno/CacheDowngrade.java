package com.moxuanran.learning.cache.anno;

import java.lang.annotation.*;

/**
 * @author moxuanran 
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheDowngrade {

}
