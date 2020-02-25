package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class WinnerState implements StateMode {

    private CandyMachine mCandyMachine;

    public WinnerState(CandyMachine candyMachine) {
        mCandyMachine = candyMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("please wait! we are giving you a candy!");

    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet!");
    }

    @Override
    public void turnCrank() {
        System.out.println("we are giving you a candy, turing another get a candy!");
    }

    @Override
    public void dispense() {
        mCandyMachine.releaseCandy();
        if (mCandyMachine.getCount() == 0) {
            mCandyMachine.setState(mCandyMachine.mSoldOutState);
        } else {
            System.out.println("you are a winner! you get another candy");
            mCandyMachine.releaseCandy();
            if (mCandyMachine.getCount() > 0) {
                mCandyMachine.setState(mCandyMachine.mOnReadyState);
            } else {
                System.out.println("Oo, out of candies");
                mCandyMachine.setState(mCandyMachine.mSoldOutState);
            }
        }

    }

    @Override
    public void printstate() {
        System.out.println("***WinnerState***");
    }
}
