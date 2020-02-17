package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza;

/**
 * @author zhangkun
 * @time 2020-02-16 21:58
 */
public abstract class Pizza {

    protected String name;
    public abstract void prepare();

    public void bake(){
        System.out.println(name+" baking...");
    }

    public void cut(){
        System.out.println(name+" cutting...");
    }

    public void box(){
        System.out.println(name+" box...");
    }

    public void setName(String name) {
        this.name = name;
    }
}
