package com.mao.cn.learnDevelopProject.java;

import java.lang.reflect.Field;

/**
 * @author zhangkun
 *uuuujk[8ime 2020-03-21 18:10
 */
public class RefMain {

    @TestInterface(20)
    private int age;

    public static void main(String[] args) {

        RefMain refMain = new RefMain();
        Class clazz = refMain.getClass();
        try {
            Field age = clazz.getDeclaredField("age");
            TestInterface annotation = age.getAnnotation(TestInterface.class);
            System.out.println(annotation.value());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
