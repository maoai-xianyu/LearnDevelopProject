package com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.bridge;

import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.control.SharpControl;
import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.tvControl.LGTvControl;
import com.mao.cn.learnDevelopProject.designPattern.Pattern15_bridgePattern.tvControl.SonyTvControl;

/**
 * @author zhangkun
 * @time 2020-02-25 21:00
 */
public class MainTest {
    public static void main(String[] args) {
        TvControlNew sonyControlNew = new TvControlNew(new SonyTvControl());
        TvControlNew lgControlNew = new TvControlNew(new LGTvControl());

        lgControlNew.onOff();
        lgControlNew.nextChannel();
        lgControlNew.nextChannel();
        lgControlNew.preChannel();
        lgControlNew.onOff();

        sonyControlNew.onOff();
        sonyControlNew.preChannel();
        sonyControlNew.preChannel();
        sonyControlNew.preChannel();
        sonyControlNew.onOff();

        TvControlExtendNew tvControlExtendNew = new TvControlExtendNew(new SharpControl());

        tvControlExtendNew.onOff();
        tvControlExtendNew.nextChannel();
        tvControlExtendNew.setChannel(9);
        tvControlExtendNew.setChannel(28);
        tvControlExtendNew.back();
        tvControlExtendNew.onOff();


    }
}
