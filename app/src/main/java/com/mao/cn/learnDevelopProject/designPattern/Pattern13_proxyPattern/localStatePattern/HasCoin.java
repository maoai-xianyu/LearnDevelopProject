package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

import java.util.Random;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class HasCoin implements StateMode {

    private CandyMachine mCandyMachine;

    public HasCoin(CandyMachine candyMachine) {
        mCandyMachine = candyMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("you can not insert another coin.");
    }

    @Override
    public void returnCoin() {
        System.out.println("coin return.");
        mCandyMachine.setState(mCandyMachine.mOnReadyState);
    }

    @Override
    public void turnCrank() {
        System.out.println("crank turn....");
        Random ranwinner = new Random();
        int winner = ranwinner.nextInt(10);
        if (winner == 0) {
            mCandyMachine.setState(mCandyMachine.mWinnerState);
        } else {
            mCandyMachine.setState(mCandyMachine.mSoldState);
        }
    }

    @Override
    public void dispense() {

    }

    @Override
    public void printstate() {
        System.out.println("***HasCoin***");
    }
}
