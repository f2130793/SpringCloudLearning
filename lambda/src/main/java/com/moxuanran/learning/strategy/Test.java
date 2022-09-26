package com.moxuanran.learning.strategy;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/22 16:22
 */
public class Test {
    public static void main(String[] args) {
        //普通的模板方式
        Validator numberValidator = new Validator(new IsNumberCase());
        boolean b1 = numberValidator.validate("aaaa");
        Validator lowerValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerValidator.validate("bbbb");

        //lambda方式
        Validator numberValidatorLambda = new Validator(
                s -> s.matches("[\\d+]")
        );
        numberValidatorLambda.validate("aaaa");

    }

}
