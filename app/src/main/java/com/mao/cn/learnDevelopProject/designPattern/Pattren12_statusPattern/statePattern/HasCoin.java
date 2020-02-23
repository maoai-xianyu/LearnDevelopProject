package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.statePattern;

import java.util.Random;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class HasCoin implements StateMode {

    private CandyMachineState mCandyMachineState;

    public HasCoin(CandyMachineState candyMachineState) {
        mCandyMachineState = candyMachineState;
    }

    @Override
    public void insertCoin() {
        System.out.println("you can not insert another coin.");
    }

    @Override
    public void returnCoin() {
        System.out.println("coin return.");
        mCandyMachineState.setState(mCandyMachineState.mOnReadyState);
    }

    @Override
    public void turnCrank() {
        System.out.println("crank turn....");
        Random ranwinner = new Random();
        int winner = ranwinner.nextInt(10);
        if (winner == 0) {
            mCandyMachineState.setState(mCandyMachineState.mWinnerState);
        } else {
            mCandyMachineState.setState(mCandyMachineState.mSoldState);
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
