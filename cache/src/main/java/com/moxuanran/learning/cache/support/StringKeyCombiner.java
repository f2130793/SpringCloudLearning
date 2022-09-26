package com.moxuanran.learning.cache.support;

/**
 * key组合器
 *
 * @author wutao
 * @date 2020/4/14 14:14
 */
public class StringKeyCombiner {

    private final static String COLON = ":";

    public static String combine(Object... params) {
        StringBuilder key = new StringBuilder();
        for (Object param : params) {
            if (key.length() > 0) {
                key.append(COLON);
            }
            key.append(param);
        }
        return key.toString();
    }
}
