package com.mao.cn.learnDevelopProject.designPattern.Pattern12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:26
 */
public class CandyMachineState {

     StateMode mSoldOutState;
     StateMode mOnReadyState;
     StateMode mHasCoin;
     StateMode mSoldState;
     StateMode mWinnerState;

    private StateMode state;
    private int count = 0;


    public CandyMachineState(int count) {
        this.count = count;
        this.mSoldOutState = new SoldOutState(this);
        this.mOnReadyState = new OnReadyState(this);
        this.mHasCoin = new HasCoin(this);
        this.mSoldState = new SoldState(this);
        this.mWinnerState = new WinnerState(this);
        if (count > 0) {
            state = mOnReadyState;
        } else {
            state = mSoldOutState;
        }
    }

    public void setState(StateMode stateMode) {
        this.state = stateMode;
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void returnCoin() {
        state.returnCoin();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void releaseCandy() {
        if (count > 0) {
            count = count - 1;
            System.out.println("a candy rolling out!");
        }
    }

    public int getCount() {
        return count;
    }

    public void printstate() {
        state.printstate();
    }
}
