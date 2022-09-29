package com.moxuanran.learning.visitor;

import java.time.LocalDate;

/**
 * @author wutao
 * @date 2022/9/29 11:46
 */
public class Fruit extends Product implements Acceptable{
    private float weight;

    public Fruit(String name, LocalDate productDate, float price, float weight) {
        super(name, productDate, price);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
