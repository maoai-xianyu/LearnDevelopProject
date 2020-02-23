package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 18:49
 */
public class MainTestState {
    public static void main(String[] args) {
        CandyMachineState candyMachine = new CandyMachineState(6);
        candyMachine.printstate();
        candyMachine.insertCoin();
        candyMachine.printstate();

        candyMachine.turnCrank();
        candyMachine.printstate();

        candyMachine.insertCoin();
        candyMachine.printstate();

        candyMachine.turnCrank();
        candyMachine.printstate();


    }
}
