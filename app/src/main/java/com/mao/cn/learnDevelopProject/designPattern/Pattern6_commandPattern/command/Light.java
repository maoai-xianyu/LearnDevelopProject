package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command;

/**
 * @author zhangkun
 * @time 2020-02-18 10:14
 */
public class Light {
    private String loc=""; // 哪里的灯  卧室  厨房

    public Light(String loc) {
        this.loc = loc;
    }

    public void on(){
        System.out.println(loc+" On");
    }

    public void off(){
        System.out.println(loc+" off");
    }
}
