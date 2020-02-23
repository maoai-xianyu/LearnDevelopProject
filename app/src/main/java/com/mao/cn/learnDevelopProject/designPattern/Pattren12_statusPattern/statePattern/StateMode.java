package com.mao.cn.learnDevelopProject.designPattern.Pattren12_statusPattern.statePattern;

/**
 * @author zhangkun
 * @time 2020-02-23 19:26
 */
public interface StateMode {
    public void insertCoin();

    public void returnCoin();

    public void turnCrank();

    public void dispense();

    public void printstate();

}
