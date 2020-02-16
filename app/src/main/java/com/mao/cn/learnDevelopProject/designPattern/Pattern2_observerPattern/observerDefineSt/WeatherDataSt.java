package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt;

import com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt.inter.Observer;
import com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt.inter.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-02-15 22:06
 */
public class WeatherDataSt implements Subject {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;
    private List<Observer> observers;

    public WeatherDataSt() {
        observers = new ArrayList<>();
    }

    private float getmTemperature() {
        return mTemperature;
    }

    private float getmPressure() {
        return mPressure;
    }

    private float getmHumidity() {
        return mHumidity;
    }

    private void dataChange() {
        notifyObservers();
    }

    // 相当于设置天气实时改变
    public void setData(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperature = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        // 更新天气
        dataChange();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }

    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(getmTemperature(), getmPressure(), getmHumidity());
        }
    }
}
