package com.moxuanran.learning.strategy;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/22 16:20
 */
public class IsNumberCase implements ValidationStrategy{
    @Override
    public boolean valid(String s) {
        return s.matches("\\d+");
    }
}
