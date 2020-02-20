package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemodeDefine;

/**
 * @author zhangkun
 * @time 2020-02-20 16:14
 */
public abstract class HotDrinkTemplate {

    // 步骤
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (wangCondimentsHook()){
            addCondiments();
        }else {
            System.out.println("No Condiments");
        }

    }

    public boolean wangCondimentsHook() {
        return true;
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public abstract void brew();

    public void pourInCup() {
        System.out.println("Pouring in cup");
    }

    public abstract void addCondiments();
}
