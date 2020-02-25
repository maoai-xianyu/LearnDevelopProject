package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control;

/**
 * @author zhangkun
 * @time 2020-02-25 19:58
 */
public class SharpControl implements Control {

    @Override
    public void on() {
        System.out.println("*Open Sharp TV*");
    }

    @Override
    public void off() {
        System.out.println("*Off Sharp TV*");
    }

    @Override
    public void setChannel(int ch) {
        System.out.println("*The Sharp TV Channel is setted " + ch + "*");
    }

    @Override
    public void setVolume(int vol) {
        System.out.println("*The Sharp TV Volume is setted " + vol + "*");
    }
}
