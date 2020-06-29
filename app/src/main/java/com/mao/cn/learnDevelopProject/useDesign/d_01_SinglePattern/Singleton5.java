package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 00:04
 * @Description 单例设计模式 - 静态内部类（比较常用）
 */
public class Singleton5 {


    private Singleton5() {

    }

    private static class Singleton5Inner {
        private static final Singleton5 mInstance = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Singleton5Inner.mInstance;
    }
}
