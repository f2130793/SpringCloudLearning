package com.moxuanran.learning.decorator;

/**
 * @author wutao
 * @date 2022/9/27 14:56
 */
public abstract class Decorator implements Showable {
    protected Showable showable;

    public Decorator(Showable showable) {
        this.showable = showable;
    }
    @Override
    public void show() {
        showable.show();
    }
}
