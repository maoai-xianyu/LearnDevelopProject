package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.coffee;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.Drink;

/**
 * @author zhangkun
 * @time 2020-02-16 17:12
 */
public class Coffee extends Drink {
    @Override
    public float cost() {
        return getPrice();
    }
}
