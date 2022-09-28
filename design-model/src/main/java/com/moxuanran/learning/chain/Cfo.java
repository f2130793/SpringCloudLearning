package com.moxuanran.learning.chain;

/**
 * @author wutao
 * @date 2022/9/28 17:32
 */
public class Cfo extends Approver {
    public Cfo(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount < 10000) {
            System.out.println("审批通过。【财务总监" + name + "】");
        } else {
            System.out.println("驳回申请。【财务总监" + name + "】");
        }
    }
}
