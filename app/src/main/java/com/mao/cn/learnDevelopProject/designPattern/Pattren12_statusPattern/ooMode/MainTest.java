package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.ooMode;

/**
 * @author zhangkun
 * @time 2020-02-23 18:49
 */
public class MainTest {
    public static void main(String[] args) {
        CandyMachine candyMachine = new CandyMachine(1);
        candyMachine.printstate();
        candyMachine.insertCoin();
        candyMachine.printstate();
        candyMachine.turnCrank();
        candyMachine.printstate();
    }
}
