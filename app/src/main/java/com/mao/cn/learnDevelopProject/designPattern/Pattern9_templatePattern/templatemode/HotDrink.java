package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemode;

/**
 * @author zhangkun
 * @time 2020-02-20 15:38
 */
public abstract class HotDrink {

    public abstract void prepareRecipe();

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void pourInCup() {
        System.out.println("Pouring in cup");
    }

}
