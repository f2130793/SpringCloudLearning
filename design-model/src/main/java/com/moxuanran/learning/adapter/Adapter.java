package com.moxuanran.learning.adapter;

/**
 * @author wutao
 * @date 2022/9/27 15:22
 */
public class Adapter implements TriplePin {
    private final DualPin dualPin;

    public Adapter(DualPin dualPin) {
        this.dualPin = dualPin;
    }

    @Override
    public void electrify(int l, int n, int e) {
        dualPin.electrify(l, n);
    }
}
