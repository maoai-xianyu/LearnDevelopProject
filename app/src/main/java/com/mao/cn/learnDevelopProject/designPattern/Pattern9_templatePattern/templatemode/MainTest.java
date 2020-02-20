package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemode;

/**
 * @author zhangkun
 * @time 2020-02-20 15:31
 */
public class MainTest {

    public static void main(String[] args) {
        CoffeeMode coffeeMode = new CoffeeMode();
        coffeeMode.prepareRecipe();

        TeaMode teaMode = new TeaMode();
        teaMode.prepareRecipe();
    }
}
