package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.ooMode;

/**
 * @author zhangkun
 * @time 2020-02-23 18:49
 */
public class CandyMachine {
    private final int SoldOutState = 0;
    private final int OnReadyState = 1;
    private final int HasCoin = 2;
    private final int SoldState = 3;

    private int state = SoldOutState;
    private int count = 0;

    public CandyMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = OnReadyState;
        }
    }

    public void insertCoin() {
        switch (state) {
            case SoldOutState:
                System.out.println("you aren't insert coin. the machine sold");
                break;
            case OnReadyState:
                state = HasCoin;
                System.out.println("you have inserted coin. please turn on.");
                break;
            case HasCoin:
                System.out.println("you can not insert another coin.");
                break;
            case SoldState:
                System.out.println("please wait! we are giving you a candy.");
                break;
            default:
                break;
        }
    }

    public void returnCoin() {
        switch (state) {
            case SoldOutState:
                System.out.println("you can't return coin, you haven't inserted a coin");
                break;
            case OnReadyState:
                System.out.println("you haven't inserted a coin yet.");
                break;
            case HasCoin:
                System.out.println("coin return.");
                state = OnReadyState;
                break;
            case SoldState:
                System.out.println("sorry, you already have turned the crank.");
                break;
            default:
                break;
        }
    }

    public void turnCrank() {
        switch (state) {
            case SoldOutState:
                System.out.println("you turned, but there are no candies.");
                break;
            case OnReadyState:
                System.out.println("you turned,but you haven't inserted a coin.");
                break;
            case HasCoin:
                System.out.println("crank turn....");
                state = SoldState;
                dispense();
                break;
            case SoldState:
                System.out.println("we are giving you a candy,turning another coin");
                break;
            default:
                break;
        }
    }

    private void dispense() {
        count = count - 1;
        System.out.println("a candy rolling out!");
        if (count > 0) {
            state = OnReadyState;
        } else {
            System.out.println("Oo, out of candies.");
            state = SoldOutState;
        }

    }

    public void printstate() {
        switch (state) {
            case SoldOutState:
                System.out.println("***SoldOutState***");
                break;
            case OnReadyState:
                System.out.println("***OnReadyState***");
                break;
            case HasCoin:
                System.out.println("***HasCoin***");
                break;
            case SoldState:
                System.out.println("***SoldState***");
                break;
            default:
                break;
        }
    }
}
