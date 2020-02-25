package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.bridge;

import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control.Control;

/**
 * @author zhangkun
 * @time 2020-02-25 20:56
 */
public abstract class TVControlabs {
    Control mControl = null;

    public TVControlabs(Control control) {
        mControl = control;
    }

    public abstract void onOff();

    public abstract void nextChannel();

    public abstract void preChannel();

}
