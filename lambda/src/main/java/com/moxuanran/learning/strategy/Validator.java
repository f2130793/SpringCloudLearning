package com.moxuanran.learning.strategy;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/22 16:21
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.valid(s);
    }
}
