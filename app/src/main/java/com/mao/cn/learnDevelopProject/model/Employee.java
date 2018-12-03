package com.mao.cn.learnDevelopProject.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.mao.cn.learnDevelopProject.BR;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2018/11/15.
 */
public class Employee extends BaseObservable implements Serializable {

    private String firstName;
    private String lastName;
    private boolean isGoodGuy;
    public ObservableBoolean isFired;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGoodGuy = true;
        isFired.set(false);
    }

    public Employee() {
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public boolean isGoodGuy() {
        return isGoodGuy;
    }

    public void setGoodGuy(boolean goodGuy) {
        this.isGoodGuy = goodGuy;
        //notifyChange();  刷新全部

    }

    public void setFired(boolean fired) {
        isFired.set(fired);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

