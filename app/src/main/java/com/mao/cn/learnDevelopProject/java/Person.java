package com.mao.cn.learnDevelopProject.java;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-03-21 10:14
 */
public class Person<T> {

    private ArrayList mObjects = new ArrayList();

    public void add(T t) {
        mObjects.add(t);
    }
}
