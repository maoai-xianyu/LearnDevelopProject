package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemodeDefine;

/**
 * @author zhangkun
 * @time 2020-02-20 15:31
 */
public class CoffeeModeDefine extends HotDrinkDefine {

    @Override
    public void brew() {
        System.out.println("Brewing coffee");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
