package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.tvControl;

/**
 * @author zhangkun
 * @time 2020-02-25 20:09
 */
public class MainTest {
    public static void main(String[] args) {

        LGTvControl lgTvControl = new LGTvControl();
        SonyTvControl sonyTvControl = new SonyTvControl();

        lgTvControl.onOff();
        lgTvControl.nextChannel();
        lgTvControl.nextChannel();
        lgTvControl.preChannel();
        lgTvControl.onOff();

        sonyTvControl.onOff();
        sonyTvControl.preChannel();
        sonyTvControl.preChannel();
        sonyTvControl.preChannel();
        sonyTvControl.onOff();

    }
}
