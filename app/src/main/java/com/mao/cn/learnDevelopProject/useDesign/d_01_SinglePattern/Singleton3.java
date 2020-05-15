package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 00:04
 * @Description 单例设计模式 - 懒汉式 - 多线程并发
 */
public class Singleton3 {

    // 使用的时候才会去new对象，可能更加高效
    // 会有问题？多线程并发的问题，如果多个线程调用会有多个实例 如何解决
    private static Singleton3 mInstance;

    private Singleton3() {

    }

    // 同步锁，解决了线程安全的问题。但是会出现效率的问题？
    // 效率比较低，每次获取都要经过同步锁的判断 看 Singleton4
    public static synchronized Singleton3 getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton3();
        }
        return mInstance;
    }
}
