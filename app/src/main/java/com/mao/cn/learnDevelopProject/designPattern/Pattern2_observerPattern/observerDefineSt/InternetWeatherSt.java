package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt;

/**
 * @author zhangkun
 * @time 2020-02-16 11:13
 */
public class InternetWeatherSt {
    public static void main(String[] args) {

        CurrentConditionsSt currentConditionsSt = new CurrentConditionsSt();
        ForcastConditionsSt forcastConditionsSt = new ForcastConditionsSt();

        WeatherDataSt weatherData = new WeatherDataSt();
        weatherData.registerObserver(currentConditionsSt);
        weatherData.registerObserver(forcastConditionsSt);
        weatherData.setData(17, 90, 40);

        System.out.println("-------------");
        weatherData.removeObserver(currentConditionsSt);
        weatherData.setData(18, 100, 50);

    }
}
