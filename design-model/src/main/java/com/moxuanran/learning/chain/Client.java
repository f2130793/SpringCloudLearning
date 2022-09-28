package com.moxuanran.learning.chain;

/**
 * @author wutao
 * @date 2022/9/28 17:35
 */
public class Client {
    public static void main(String[] args) {
        Approver flight = new Staff("张飞");
        flight.setNextApprover(new Manager("关羽")).setNextApprover(new Cfo("刘备"));
        flight.approve(100);
        flight.approve(2000);
        flight.approve(6000);
    }
}
