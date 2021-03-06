package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerJava;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhangkun
 * @time 2020-02-15 22:14
 */
public class CurrentConditionsJava implements Observer {
    private float mTemperature;
    private float mPressure;
    private float mHumidity;


    @Override
    public void update(Observable o, Object arg) {
        this.mTemperature = ((WeatherDataJava.Data) arg).getmTemperature();
        this.mPressure = ((WeatherDataJava.Data) arg).getmPressure();
        this.mHumidity = ((WeatherDataJava.Data) arg).getmHumidity();
        display();
    }

    private void display() {
        System.out.println("***Today mTemperature: " + mTemperature + "***");
        System.out.println("***Today mPressure: " + mPressure + "***");
        System.out.println("***Today mHumidity: " + mHumidity + "***");
    }
}
