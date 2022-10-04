package com.moxuanran.learning.formatterspringbootstarter;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/26 21:24
 */
public interface Formatter {
    /**
     * 格式
     *
     * @param object 对象
     * @return {@link String}
     */
    String format(Object object);
}
