package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefine;

/**
 * @author zhangkun
 * @time 2020-02-15 22:14
 */
public class CurrentConditions {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;

    public void update(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperature = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        display();
    }

    private void display() {
        System.out.println("***Today mTemperature: " + mTemperature + "***");
        System.out.println("***Today mPressure: " + mPressure + "***");
        System.out.println("***Today mHumidity: " + mHumidity + "***");
    }
}
