package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:11
 */
public class Screen {
    //屏幕
    private static Screen instance = null;

    private Screen() {

    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }

    public void up() {
        System.out.println("Screen up");
    }

    public void down() {
        System.out.println("Screen down");
    }

}
