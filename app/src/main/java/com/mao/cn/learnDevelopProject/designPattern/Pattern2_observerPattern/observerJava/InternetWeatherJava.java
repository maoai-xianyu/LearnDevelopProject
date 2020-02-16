package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerJava;

/**
 * @author zhangkun
 * @time 2020-02-16 11:13
 */
public class InternetWeatherJava {
    public static void main(String[] args) {

        CurrentConditionsJava currentConditionsJava = new CurrentConditionsJava();
        ForcastConditionsJava forcastConditionsJava = new ForcastConditionsJava();

        WeatherDataJava weatherDataJava = new WeatherDataJava();
        weatherDataJava.addObserver(currentConditionsJava);
        weatherDataJava.addObserver(forcastConditionsJava);
        weatherDataJava.setData(17, 90, 40);

        System.out.println("-------------");
        weatherDataJava.deleteObserver(currentConditionsJava);
        weatherDataJava.setData(18, 100, 50);

    }
}
