package com.moxuanran.learning.decorator;

/**
 * @author wutao
 * @date 2022/9/27 15:01
 */
public class FoundationMakeup extends Decorator{
    public FoundationMakeup(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.println("[打粉底");
        showable.show();
        System.out.println("]");
    }
}
