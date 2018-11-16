package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2018/11/15.
 */
public class EmployeeUser implements Serializable {

    private String firstName;
    private String lastName;
    private boolean isGoodGuy;

    public EmployeeUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGoodGuy = true;
    }

    public EmployeeUser() {
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

    public boolean isGoodGuy() {
        return isGoodGuy;
    }

    public void setGoodGuy(boolean goodGuy) {
        isGoodGuy = goodGuy;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

