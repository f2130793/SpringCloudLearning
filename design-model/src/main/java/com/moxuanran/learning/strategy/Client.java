package com.moxuanran.learning.strategy;

/**
 * @author wutao
 * @date 2022/9/29 10:20
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        USB keyBoard = new KeyBoard();
        computer.setUsb(keyBoard);
        computer.compute();
    }
}
