package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 10:50
 * @Description 单例设计模式
 */
public class Singleton8 {

    private static Singleton8 mInstance;

    static {
        mInstance = new Singleton8();
    }

    private Singleton8() {

    }


    public static Singleton8 getInstance() {
        return mInstance;
    }
}
