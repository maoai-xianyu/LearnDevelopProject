package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 18:49
 */
public class MainTestLocalState {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        CandyMachine candyMachine = new CandyMachine("datong", 6);
        monitor.addMachine(candyMachine);

        candyMachine = new CandyMachine("zy",4);
        candyMachine.insertCoin();
        monitor.addMachine(candyMachine);

        candyMachine = new CandyMachine("kwj",3);
        candyMachine.insertCoin();
        candyMachine.turnCrank();
        monitor.addMachine(candyMachine);

        monitor.report();


    }
}
