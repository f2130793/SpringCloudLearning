package com.moxuanran.learning.bridging;

/**
 * @author wutao
 * @date 2022/9/28 15:33
 */
public abstract class Pen {
    protected Ruler ruler;
    public Pen(Ruler ruler) {
        this.ruler = ruler;
    }
    /**
     * 画
     */
    public abstract void draw();
}
