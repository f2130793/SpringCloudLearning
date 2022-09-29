package com.moxuanran.learning.visitor;

/**
 * @author wutao
 * @date 2022/9/29 11:48
 */
public interface Visitor {
    /**
     * 访问
     *
     * @param candy 糖果
     */
    void visit(Candy candy);

    /**
     * 访问
     *
     * @param wine 酒
     */
    void visit(Wine wine);

    /**
     * 访问
     *
     * @param fruit 水果
     */
    void visit(Fruit fruit);
}
