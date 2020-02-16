package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.Drink;

/**
 * @author zhangkun
 * @time 2020-02-16 17:43
 */
public class Soy extends Decorator {

    public Soy(Drink obj) {
        super(obj);
        super.setDescription("Soy");
        super.setPrice(3);
    }
}
