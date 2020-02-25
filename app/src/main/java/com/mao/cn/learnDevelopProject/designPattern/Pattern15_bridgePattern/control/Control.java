package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control;

/**
 * @author zhangkun
 * @time 2020-02-25 19:57
 */
public interface Control {

    public void on();

    public void off();

    public void setChannel(int ch);

    public void setVolume(int vol);
}
