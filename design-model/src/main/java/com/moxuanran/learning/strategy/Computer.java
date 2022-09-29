package com.moxuanran.learning.strategy;

/**
 * @author wutao
 * @date 2022/9/29 10:18
 */
public class Computer {
    private USB usb;
    public void setUsb(USB usb) {
        this.usb = usb;
    }

    public void compute() {
        usb.read();
    }
}
