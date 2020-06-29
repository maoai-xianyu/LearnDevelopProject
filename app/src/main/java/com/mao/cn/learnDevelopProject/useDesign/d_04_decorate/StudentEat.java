package com.mao.cn.learnDevelopProject.useDesign.d_04_decorate;

/**
 * @author zhangkun
 * @time 2020-05-18 22:07
 * @Description
 */
public class StudentEat implements Eat{

    private Eat mEat;

    public StudentEat(PersonEat eat){
        this.mEat = eat;
    }

    @Override
    public void eat() {

        System.out.println("学生打饭");
        mEat.eat();
        System.out.println("学生大菜");
    }
}
