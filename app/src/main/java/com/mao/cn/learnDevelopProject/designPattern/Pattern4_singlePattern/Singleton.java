package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 21:19
 */
public class Singleton {


    // 饱汉模式(懒汉模式)
    // 优点：懒加载启动快，资源占用小，使用时才实例化，无锁
    // 缺点：非线程安全。

    private static Singleton uniqueInterstance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (uniqueInterstance == null) {
            uniqueInterstance = new Singleton();
        }
        return uniqueInterstance;
    }
}
