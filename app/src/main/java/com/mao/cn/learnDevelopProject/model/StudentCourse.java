package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2017/8/11.
 */

public class StudentCourse implements Serializable {

    private String course_name;
    private long course_price;

    public StudentCourse() {
    }

    public StudentCourse(String course_name, long course_price) {
        this.course_name = course_name;
        this.course_price = course_price;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public long getCourse_price() {
        return course_price;
    }

    public void setCourse_price(long course_price) {
        this.course_price = course_price;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "course_name='" + course_name + '\'' +
                ", course_price=" + course_price +
                '}';
    }
}
