package com.moxuanran.learning.strategy;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/22 16:19
 */
public class IsAllLowerCase implements ValidationStrategy{
    @Override
    public boolean valid(String s) {
        return s.matches("[a-z]+");
    }
}
