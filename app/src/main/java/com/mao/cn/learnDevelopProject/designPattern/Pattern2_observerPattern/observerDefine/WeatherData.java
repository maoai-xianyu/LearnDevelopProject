package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefine;

/**
 * @author zhangkun
 * @time 2020-02-15 22:06
 */
public class WeatherData {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;
    private CurrentConditions mCurrentConditions;

    public WeatherData(CurrentConditions mCurrentConditions) {
        this.mCurrentConditions = mCurrentConditions;
    }

    public float getmTemperature() {
        return mTemperature;
    }

    public float getmPressure() {
        return mPressure;
    }

    public float getmHumidity() {
        return mHumidity;
    }

    public void dataChange() {
        mCurrentConditions.update(getmTemperature(), getmPressure(), getmHumidity());
    }

    // 相当于设置天气实时改变
    public void setData(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperature = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        // 更新天气
        dataChange();
    }

}
