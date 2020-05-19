package com.mao.cn.learnDevelopProject.useDesign.d_04_decorate;

/**
 * @author zhangkun
 * @time 2020-05-18 22:07
 * @Description
 */
public class TeacherEat implements Eat{

    private Eat mEat;

    public TeacherEat(PersonEat eat){
        this.mEat = eat;
    }

    @Override
    public void eat() {

        System.out.println("老师打饭");
        mEat.eat();
        System.out.println("老师喝汤");
    }
}
