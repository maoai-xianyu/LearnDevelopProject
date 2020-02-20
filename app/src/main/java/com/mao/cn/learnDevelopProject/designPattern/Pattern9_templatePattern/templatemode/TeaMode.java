package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemode;

/**
 * @author zhangkun
 * @time 2020-02-20 15:31
 */
public class TeaMode extends HotDrink {

    public void prepareRecipe(){
        boilWater();
        brewTea();
        pourInCup();
        addLemon();
    }


    private void brewTea() {
        System.out.println("Brewing tea");
    }


    private void addLemon() {
        System.out.println("Adding lemon");
    }
}
