package com.moxuanran.learning.visitor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/29 11:51
 */
public class Client {
    public static void main(String[] args) {
        List<Acceptable> products = Arrays.asList(
                new Candy("小白兔奶糖", LocalDate.of(2018, 10, 1), 20f),
                new Wine("老酒", LocalDate.of(2018, 10, 1), 20f),
                new Fruit("草莓", LocalDate.of(2018, 10, 1), 20f, 2.5f)
        );

        DiscountVisitor discountVisitor = new DiscountVisitor(LocalDate.of(2020, 1, 1));
        for (Acceptable product : products) {
            product.accept(discountVisitor);
        }
    }
}
