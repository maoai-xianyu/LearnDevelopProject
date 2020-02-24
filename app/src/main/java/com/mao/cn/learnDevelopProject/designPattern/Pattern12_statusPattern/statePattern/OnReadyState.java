package com.mao.cn.learnDevelopProject.designPattern.Pattern12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:29
 */
public class OnReadyState implements StateMode {

    private CandyMachineState mCandyMachineState;

    public OnReadyState(CandyMachineState candyMachineState) {
        mCandyMachineState = candyMachineState;
    }

    @Override
    public void insertCoin() {
        System.out.println("you have inserted coin. please turn crank!");
        mCandyMachineState.setState(mCandyMachineState.mHasCoin);
    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet.");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned,but you haven't inserted a coin.");
    }

    @Override
    public void dispense() {

    }

    @Override
    public void printstate() {
        System.out.println("***OnReadyState***");
    }
}
