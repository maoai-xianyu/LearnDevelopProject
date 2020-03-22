package com.mao.cn.learnDevelopProject.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2018/11/15.
 */
public class Employees extends BaseObservable implements Serializable {

    private String firstName;
    private String lastName;
    public ObservableBoolean isFired = new ObservableBoolean();

    public Employees(String firstName, String lastName, boolean isfired) {
        this.firstName = firstName;
        this.lastName = lastName;
        isFired.set(isfired);
    }

    public Employees() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}

