package com.moxuanran.learning.visitor;

import java.time.LocalDate;

/**
 * @author wutao
 * @date 2022/9/29 11:49
 */
public class DiscountVisitor implements Visitor {
    private LocalDate billDate;
    public DiscountVisitor(LocalDate billDate) {
        this.billDate = billDate;
        System.out.println("结算日期:" + billDate);
    }
    /**
     * 访问
     *
     * @param candy 糖果
     */
    @Override
    public void visit(Candy candy) {
        System.out.println("糖果打折了");
    }

    /**
     * 访问
     *
     * @param wine 酒
     */
    @Override
    public void visit(Wine wine) {
        System.out.println("酒水原价");
    }

    /**
     * 访问
     *
     * @param fruit 水果
     */
    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果称重");
    }
}
