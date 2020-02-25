package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

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
