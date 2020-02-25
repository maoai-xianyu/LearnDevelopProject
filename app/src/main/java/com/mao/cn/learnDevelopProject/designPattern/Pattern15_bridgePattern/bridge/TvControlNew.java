package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.bridge;

import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control.Control;

/**
 * @author zhangkun
 * @time 2020-02-25 20:58
 */
public class TvControlNew extends TVControlabs {

    private int ch = 0;
    private boolean ison = false;

    public TvControlNew(Control control) {
        super(control);
    }

    @Override
    public void onOff() {
        if (ison) {
            ison = false;
            mControl.off();
        } else {
            ison = true;
            mControl.on();
        }
    }

    @Override
    public void nextChannel() {
        ch++;
        mControl.setChannel(ch);

    }

    @Override
    public void preChannel() {
        ch--;
        if (ch < 0) {
            ch = 200;
        }
        mControl.setChannel(ch);

    }
}
