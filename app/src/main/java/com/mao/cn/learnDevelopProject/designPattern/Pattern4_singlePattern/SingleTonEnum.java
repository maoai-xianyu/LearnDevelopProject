package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020/8/14 4:25 PM
 * @Description
 */
enum SingleTonEnum {
    INSTANCE;

    public void doSomeThing(){
        System.out.println("do some thing");
    }
}
