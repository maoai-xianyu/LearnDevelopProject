package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.Drink;

/**
 * @author zhangkun
 * @time 2020-02-16 17:42
 */
public class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        super.setDescription("Milk");
        super.setPrice(3);
    }
}
