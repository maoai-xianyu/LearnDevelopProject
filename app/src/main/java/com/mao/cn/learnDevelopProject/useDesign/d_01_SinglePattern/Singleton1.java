package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 00:01
 * @Description  单例设计模式- 饿汉式
 */
public class Singleton1 {

    // 随着类的加载就已经new了对象
    private static Singleton1 mInstance = new Singleton1();


    private Singleton1() {

    }


    public static Singleton1 getInstance() {
        return mInstance;
    }
}
