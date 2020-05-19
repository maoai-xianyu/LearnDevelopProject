package com.mao.cn.learnDevelopProject.useDesign.d_04_decorate;

/**
 * @author zhangkun
 * @time 2020-05-18 22:06
 * @Description
 */
public class PersonEat implements Eat {

    @Override
    public void eat() {
        System.out.println("吃饭好香");
    }
}
