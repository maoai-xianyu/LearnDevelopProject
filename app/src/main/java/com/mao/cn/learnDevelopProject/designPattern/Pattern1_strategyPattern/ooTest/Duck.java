package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-02-14 21:02
 */
public abstract class Duck {
    public Duck() {
    }

    // 自有数据
    public void quack(){
        System.out.println("~~gaga~~");
    }

    public void swim(){
        System.out.println("~~swim~~");
    }

    public abstract void display();

    public void fly(){
        System.out.println("~~fly~~");
    }
}
