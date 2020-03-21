package com.mao.cn.learnDevelopProject.java;

/**
 * @author zhangkun
 * @time 2020-03-20 22:21
 */
public class TestBean {

    private String name;

    private TestBean(String name) {
        this.name = name;
    }

    public TestBean() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sysName() {
        System.out.println("name  " + name);
    }

    private void sayThisName(){
        System.out.println("this name  " + name);
    }
}
