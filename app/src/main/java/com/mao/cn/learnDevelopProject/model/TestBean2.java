package com.mao.cn.learnDevelopProject.model;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class TestBean2 implements Cloneable {
    private String name;
    private String desc;
    private int pic;

    public TestBean2(String name, String desc, int pic) {
        this.name = name;
        this.desc = desc;
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //仅写DEMO 用 实现克隆方法

    @Override
    public TestBean2 clone() throws CloneNotSupportedException {
        TestBean2 bean = null;
        try {
            bean = (TestBean2) super.clone();
        } catch (CloneNotSupportedException e) {
            LogU.e(" e" + e.toString());
        }
        return bean;
    }
}