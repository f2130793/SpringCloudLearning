package com.moxuanran.learning.visitor;

import java.time.LocalDate;

/**
 * @author wutao
 * @date 2022/9/29 11:46
 */
public class Candy extends Product implements Acceptable{
    public Candy(String name, LocalDate productDate, float price) {
        super(name, productDate, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
