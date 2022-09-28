package com.moxuanran.learning.chain;

/**
 * @author wutao
 * @date 2022/9/28 17:28
 */
public abstract class Approver {
    protected String name;
    protected Approver nextApprover;

    public Approver(String name) {
        this.name = name;
    }

    protected Approver setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
        return this.nextApprover;
    }

    /**
     * 批准
     *
     * @param amount 量
     */
    public abstract void approve(int amount);

}
