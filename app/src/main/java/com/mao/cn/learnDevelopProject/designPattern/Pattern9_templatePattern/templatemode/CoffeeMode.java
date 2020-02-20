package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemode;

/**
 * @author zhangkun
 * @time 2020-02-20 15:31
 */
public class CoffeeMode extends HotDrink {

    public void prepareRecipe() {
        boilWater();
        brewCoffee();
        pourInCup();
        addSugarMilk();
    }

    private void brewCoffee() {
        System.out.println("Brewing coffee");
    }

    private void addSugarMilk() {
        System.out.println("Adding sugar and milk");
    }
}
