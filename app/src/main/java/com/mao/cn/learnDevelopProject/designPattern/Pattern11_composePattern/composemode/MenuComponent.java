package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;


import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-23 11:04
 */
public abstract class MenuComponent {

    public String getName() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public float getPrice() {
        return 0;
    }

    public boolean isVegetable() {
        return false;
    }

    public abstract void print();

    public Iterator getIterator() {
        return new NullIterator();
    }
}
