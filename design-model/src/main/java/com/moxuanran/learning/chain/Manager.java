package com.moxuanran.learning.chain;

/**
 * @author wutao
 * @date 2022/9/28 17:32
 */
public class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount < 5000) {
            System.out.println("审批通过。【经理" + name + "】");
        } else {
            System.out.println("无权审批，升级处理。【经理" + name + "】");
            this.nextApprover.approve(amount);
        }
    }
}
