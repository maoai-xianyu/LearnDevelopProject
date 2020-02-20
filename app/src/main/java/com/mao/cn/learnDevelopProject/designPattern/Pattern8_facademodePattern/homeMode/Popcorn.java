package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:10
 */
public class Popcorn {
    // 爆米花机
    private static Popcorn instance = null;

    private Popcorn() {
    }

    public static Popcorn getInstance() {
        if (instance == null) {
            instance = new Popcorn();
        }
        return instance;
    }

    public void on(){
        System.out.println("Popcorn on");
    }

    public void off(){
        System.out.println("Popcorn off");
    }

    public void pop(){
        System.out.println("Popcorn is popping");
    }
}
