package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 00:04
 * @Description 单例设计模式 - 懒汉式
 */
public class Singleton2 {

    // 使用的时候才会去new对象，可能更加高效
    // 会有问题？多线程并发的问题，如果多个线程调用会有多个实例
    private static Singleton2 mInstance;

    private Singleton2() {

    }


    public static Singleton2 getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton2();
        }
        return mInstance;
    }
}
