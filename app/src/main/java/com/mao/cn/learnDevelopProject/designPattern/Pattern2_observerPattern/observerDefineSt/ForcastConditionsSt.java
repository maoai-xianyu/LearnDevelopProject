package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt;

import com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt.inter.Observer;

/**
 * @author zhangkun
 * @time 2020-02-15 22:14
 */
public class ForcastConditionsSt implements Observer {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperature = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        display();
    }

    private void display() {
        System.out.println("***明天 mTemperature: " + (mTemperature + Math.random()) + "***");
        System.out.println("***明天 mPressure: " + (mPressure + Math.random()) + "***");
        System.out.println("***明天 mHumidity: " + (mHumidity + Math.random()) + "***");
    }
}
