package com.moxuanran.learning.cache.support;

import com.alibaba.fastjson.JSON;

/**
 * key转换器
 *
 * @author moxuanran 
 * 
 */
public class KeyConverter {


    public static String convert(Object originalKey) {
        if (originalKey == null) {
            return null;
        }
        if (originalKey instanceof String) {
            return (String) originalKey;
        }
        return JSON.toJSONString(originalKey);
    }

}
