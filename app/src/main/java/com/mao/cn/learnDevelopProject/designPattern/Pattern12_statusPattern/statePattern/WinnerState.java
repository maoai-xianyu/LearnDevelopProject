package com.mao.cn.learnDevelopProject.designPattern.Pattern12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class WinnerState implements StateMode {

    private CandyMachineState mCandyMachineState;

    public WinnerState(CandyMachineState candyMachineState) {
        mCandyMachineState = candyMachineState;
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
        mCandyMachineState.releaseCandy();
        if (mCandyMachineState.getCount() == 0) {
            mCandyMachineState.setState(mCandyMachineState.mSoldOutState);
        } else {
            System.out.println("you are a winner! you get another candy");
            mCandyMachineState.releaseCandy();
            if (mCandyMachineState.getCount() > 0) {
                mCandyMachineState.setState(mCandyMachineState.mOnReadyState);
            } else {
                System.out.println("Oo, out of candies");
                mCandyMachineState.setState(mCandyMachineState.mSoldOutState);
            }
        }

    }

    @Override
    public void printstate() {
        System.out.println("***WinnerState***");
    }
}
