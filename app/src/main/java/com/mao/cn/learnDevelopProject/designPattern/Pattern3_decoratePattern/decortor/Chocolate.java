package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.Drink;

/**
 * @author zhangkun
 * @time 2020-02-16 17:40
 */
public class Chocolate extends Decorator {


    public Chocolate(Drink obj) {
        super(obj);
        super.setDescription("Chocolate");
        super.setPrice(8);
    }
}
