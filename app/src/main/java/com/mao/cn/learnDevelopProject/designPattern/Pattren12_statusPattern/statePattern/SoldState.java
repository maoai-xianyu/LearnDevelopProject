package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class SoldState implements StateMode {

    private CandyMachineState mCandyMachineState;

    public SoldState(CandyMachineState candyMachineState) {
        mCandyMachineState = candyMachineState;
    }

    @Override
    public void insertCoin() {
        System.out.println("please wait! we are giving you a candy.");
    }

    @Override
    public void returnCoin() {
        System.out.println("sorry, you already have turned the crank.");
    }

    @Override
    public void turnCrank() {
        System.out.println("we are giving you a candy,turning another coin");
    }

    @Override
    public void dispense() {
        mCandyMachineState.releaseCandy();
        if (mCandyMachineState.getCount() > 0) {
            mCandyMachineState.setState(mCandyMachineState.mOnReadyState);
        } else {
            System.out.println("Oo, out of candies.");
            mCandyMachineState.setState(mCandyMachineState.mSoldOutState);
        }

    }

    @Override
    public void printstate() {
        System.out.println("***SoldState***");
    }
}
