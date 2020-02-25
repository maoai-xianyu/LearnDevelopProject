package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.bridge;

import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control.Control;

/**
 * @author zhangkun
 * @time 2020-02-25 21:04
 */
public class TvControlExtendNew extends TVControlabs {

    private int ch = 0;
    private boolean ison = false;
    private int prech = 0;

    public TvControlExtendNew(Control control) {
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
        prech = ch;
        ch++;
        mControl.setChannel(ch);

    }

    @Override
    public void preChannel() {
        prech = ch;
        ch--;
        if (ch < 0) {
            ch = 200;
        }
        mControl.setChannel(ch);
    }

    public void setChannel(int nch) {
        prech = ch;
        ch = nch;
        mControl.setChannel(ch);
    }

    public void back() {
        mControl.setChannel(prech);
    }
}
