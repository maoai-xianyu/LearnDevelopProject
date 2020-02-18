package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 21:19
 */
public class Singleton {

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
