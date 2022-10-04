package com.moxuanran.learning.decorator;

/**
 * @author wutao
 * @date 2022/9/27 15:01
 */
public class Lipstick extends Decorator{
    public Lipstick(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.println("[涂口红");
        showable.show();
        System.out.println("]");
    }
}
