package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.oo;

/**
 * @author zhangkun
 * @time 2020-03-02 13:58
 */
public class OoTree {

    private int xCoord,yCoord,age;

    public OoTree(int xCoord, int yCoord, int age) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.age = age;
    }

    public void display(){
        // 如果是 1000000,那么很消耗内存
        //System.out.println("tree x = "+xCoord+" y "+yCoord+" age "+age);
    }
}
