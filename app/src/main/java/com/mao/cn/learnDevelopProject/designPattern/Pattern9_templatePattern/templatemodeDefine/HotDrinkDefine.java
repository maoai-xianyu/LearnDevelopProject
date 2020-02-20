package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemodeDefine;

/**
 * @author zhangkun
 * @time 2020-02-20 16:02
 */
public abstract class HotDrinkDefine {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public abstract void brew();

    public void pourInCup() {
        System.out.println("Pouring in cup");
    }

    public abstract  void addCondiments();

}
