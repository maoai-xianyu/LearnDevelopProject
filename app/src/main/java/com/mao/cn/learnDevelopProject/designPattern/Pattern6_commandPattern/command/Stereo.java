package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command;

/**
 * @author zhangkun
 * @time 2020-02-18 10:18
 */
public class Stereo {
    static int volume = 0;

    public void on() {
        System.out.println("Stereo On");
    }

    public void off() {
        System.out.println("Stereo Off");
    }

    public void setCd() {
        System.out.println("Stereo SetCd");
    }

    public void setVol(int vol) {
        volume = vol;
        System.out.println("Stereo volume= " + volume);
    }

    public int getVol() {
        return volume;
    }

    public void start() {
        System.out.println("Stereo Start");
    }


}
