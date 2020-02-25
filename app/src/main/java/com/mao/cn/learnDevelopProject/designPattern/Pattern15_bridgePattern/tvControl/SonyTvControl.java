package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.tvControl;

import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control.SonyControl;

/**
 * @author zhangkun
 * @time 2020-02-25 20:05
 */
public class SonyTvControl extends SonyControl implements TVControl {

    private static int ch = 0;
    private static boolean ison = false;

    @Override
    public void onOff() {
        if (ison) {
            ison = false;
            super.off();
        } else {
            ison = true;
            super.on();
        }
    }

    @Override
    public void nextChannel() {
        ch++;
        super.setChannel(ch);

    }

    @Override
    public void preChannel() {
        ch--;
        if (ch < 0) {
            ch = 200;
        }
        super.setChannel(ch);

    }
}
