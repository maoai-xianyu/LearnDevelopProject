package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.flyPatternExtend;

/**
 * @author zhangkun
 * @time 2020-03-02 15:21
 */
public class TreeNew extends Plant {
    @Override
    public void display(int xCoord, int yCoord, int age) {
        // 如果是 1000000,那么很消耗内存
        //System.out.println("tree x = "+xCoord+" y "+yCoord+" age "+age);
    }
}
