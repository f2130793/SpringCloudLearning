package com.moxuanran.learning.formatterspringbootstarter;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/26 21:25
 */
public class DefaultFormatter implements Formatter{
    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}
