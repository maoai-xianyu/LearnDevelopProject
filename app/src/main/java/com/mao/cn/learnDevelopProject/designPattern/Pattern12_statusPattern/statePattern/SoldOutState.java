package com.mao.cn.learnDevelopProject.designPattern.Pattern12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class SoldOutState implements StateMode {

    private CandyMachineState mCandyMachineState;

    public SoldOutState(CandyMachineState candyMachineState) {
        mCandyMachineState = candyMachineState;
    }

    @Override
    public void insertCoin() {
        System.out.println("you aren't insert coin. the machine sold");
    }

    @Override
    public void returnCoin() {
        System.out.println("you can't return coin, you haven't inserted a coin");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned, but there are no candies.");
    }

    @Override
    public void dispense() {

    }

    @Override
    public void printstate() {
        System.out.println("***SoldOutState***");
    }
}
