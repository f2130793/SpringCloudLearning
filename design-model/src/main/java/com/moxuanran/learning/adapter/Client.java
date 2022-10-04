package com.moxuanran.learning.adapter;

/**
 * @author wutao
 * @date 2022/9/27 15:22
 */
public class Client {
    public static void main(String[] args) {
        DualPin dualPin = new TV();
        TriplePin triplePin = new Adapter(dualPin);
        triplePin.electrify(1, 0, -1);

        TvAdapter tvAdapter = new TvAdapter();
        tvAdapter.electrify(1, 0, -1);
    }
}
