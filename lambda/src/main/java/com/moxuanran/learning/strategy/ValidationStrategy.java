package com.moxuanran.learning.strategy;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/22 16:18
 */
public interface ValidationStrategy {
    /**
     * 是否有效
     *
     * @param s 年代
     * @return boolean
     */
    boolean valid(String s);
}
