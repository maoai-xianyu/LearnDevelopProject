package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class SoldOutState implements StateMode {

    private CandyMachine mCandyMachine;

    public SoldOutState(CandyMachine candyMachine) {
        mCandyMachine = candyMachine;
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
