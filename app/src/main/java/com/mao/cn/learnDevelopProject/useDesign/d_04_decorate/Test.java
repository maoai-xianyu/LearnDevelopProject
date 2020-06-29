package com.mao.cn.learnDevelopProject.useDesign.d_04_decorate;

/**
 * @author zhangkun
 * @time 2020-05-18 22:24
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        PersonEat personEat = new PersonEat();
        StudentEat studentEat = new StudentEat(personEat);

        studentEat.eat();

    }
}
