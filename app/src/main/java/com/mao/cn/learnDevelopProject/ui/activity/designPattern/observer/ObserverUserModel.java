package com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer;

/**
 * @author zhangkun
 * @time 2020-05-25 11:14
 * @Description
 */
public class ObserverUserModel {

    private String name;
    private String city;

    public ObserverUserModel() {
    }

    public ObserverUserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ObserverUserModel{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
