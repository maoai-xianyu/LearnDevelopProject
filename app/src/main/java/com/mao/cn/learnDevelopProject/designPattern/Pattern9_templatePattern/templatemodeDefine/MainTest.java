package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemodeDefine;

/**
 * @author zhangkun
 * @time 2020-02-20 15:31
 */
public class MainTest {

    public static void main(String[] args) {
        HotDrinkDefine coffeeMode = new CoffeeModeDefine();
        coffeeMode.prepareRecipe();

        HotDrinkDefine teaMode = new TeaModeDefine();
        teaMode.prepareRecipe();

        TeaWithHook teaWithHook = new TeaWithHook();
        teaWithHook.prepareRecipe();
    }
}
