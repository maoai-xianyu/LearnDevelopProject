package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.turkey;

/**
 * @author zhangkun
 * @time 2020-02-19 10:27
 */
public class WildTurkey implements Turkey {

    @Override
    public void fly() {
        System.out.println("I am flying a short distance");
    }

    @Override
    public void gobble() {
        System.out.println(" Go Go");
    }
}
