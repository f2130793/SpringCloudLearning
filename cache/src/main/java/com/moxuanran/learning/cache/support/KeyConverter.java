package com.moxuanran.learning.cache.support;

import com.alibaba.fastjson.JSON;

/**
 * key转换器
 *
 * @author wutao
 * @date 2020/4/10 17:56
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
