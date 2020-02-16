package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefine;

/**
 * @author zhangkun
 * @time 2020-02-16 11:13
 */
public class InternetWeather {
    public static void main(String[] args) {

        CurrentConditions currentConditions = new CurrentConditions();

        WeatherData weatherData = new WeatherData(currentConditions);

        weatherData.setData(17, 90, 40);

    }
}
