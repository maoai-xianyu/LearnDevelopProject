package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.flyPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 14:39
 */
public class TreeManager {
    private int length = 10000000;

    private int[] xArray = new int[length];
    private int[] yArray = new int[length];
    private int[] ageArray = new int[length];

    private TreeFlyWeight mTreeFlyWeight;

    public TreeManager() {
        mTreeFlyWeight = new TreeFlyWeight();
        for (int i = 0; i < length; i++) {
            xArray[i] = (int)(Math.random() * length);
            yArray[i] = (int)(Math.random() * length);
            ageArray[i] = (int)(Math.random() * length) % 5;
        }
    }

    public void display(){
        for (int i = 0; i < length; i++) {
            mTreeFlyWeight.display(xArray[i],yArray[i],ageArray[i]);
        }
    }
}
