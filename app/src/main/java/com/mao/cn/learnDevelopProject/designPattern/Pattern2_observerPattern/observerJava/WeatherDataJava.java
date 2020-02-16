package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerJava;

import java.util.Observable;

/**
 * @author zhangkun
 * @time 2020-02-15 22:06
 */
public class WeatherDataJava extends Observable {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;


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
        this.setChanged();
        //this.notifyObservers();
        this.notifyObservers(new Data(getmTemperature(), getmPressure(), getmHumidity()));
    }

    // 相当于设置天气实时改变
    public void setData(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperature = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        // 更新天气
        dataChange();
    }

    public class Data {
        private float mTemperature;
        private float mPressure;
        private float mHumidity;

        public Data(float mTemperature, float mPressure, float mHumidity) {
            this.mTemperature = mTemperature;
            this.mPressure = mPressure;
            this.mHumidity = mHumidity;
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

        @Override
        public String toString() {
            return "Data{" +
                    "mTemperature=" + mTemperature +
                    ", mPressure=" + mPressure +
                    ", mHumidity=" + mHumidity +
                    '}';
        }
    }
}
