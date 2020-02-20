package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:11
 */
public class Projector {
    // 投影仪
    private static Projector instance = null;
    private int size = 0;

    private Projector() {

    }

    public static Projector getInstance() {
        if (instance == null) {
            instance = new Projector();
        }
        return instance;
    }

    public void on() {
        System.out.println("Projector on");
    }

    public void off() {
        System.out.println("Projector off");
    }

    public void focus() {
        System.out.println("Projector is focus");
    }

    public void zoom(int size) {
        this.size = size;
        System.out.println("Projector zoom to " + size);
    }
}
