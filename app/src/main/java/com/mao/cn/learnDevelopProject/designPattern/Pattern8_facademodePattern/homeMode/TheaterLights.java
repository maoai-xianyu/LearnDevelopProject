package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:11
 */
public class TheaterLights {
    // 灯光
    private static TheaterLights instance = null;

    private TheaterLights() {

    }

    public static TheaterLights getInstance() {
        if (instance == null) {
            instance = new TheaterLights();
        }
        return instance;
    }

    public void on() {
        System.out.println("TheaterLights on");
    }

    public void off() {
        System.out.println("TheaterLights off");
    }

    public void dim(int d) {
        System.out.println("TheaterLights dim to " + d + "%");
    }

    public void bright() {
        dim(100);
        System.out.println("TheaterLights bright");
    }
}
