package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.Drink;

/**
 * @author zhangkun
 * @time 2020-02-16 17:34
 */
public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice() + obj.cost();
    }


    @Override
    public String getDescription() {
        return super.description + "-" + super.getPrice() + "&&" +
                obj.getDescription();
    }
}
