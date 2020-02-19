package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck;

/**
 * @author zhangkun
 * @time 2020-02-19 10:25
 */
public class GreenHeadDuckAdapter implements DuckAdapter {

    @Override
    public void fly() {
        System.out.println("I am flying a long distance");
    }

    @Override
    public void quack() {
        System.out.println(" Ga Ga");
    }
}
