package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.flyPatternExtend;

/**
 * @author zhangkun
 * @time 2020-03-02 15:22
 */
public class PlantManager {

    private int length = 10000000;

    private int[] xArray = new int[length];
    private int[] yArray = new int[length];
    private int[] ageArray = new int[length];
    private int[] typeArray = new int[length];
    private PlantFactory mPlantFactory;

    public PlantManager() {
        mPlantFactory = new PlantFactory();
        for (int i = 0; i < length; i++) {
            xArray[i] = (int) (Math.random() * length);
            yArray[i] = (int) (Math.random() * length);
            ageArray[i] = (int) (Math.random() * length) % 5;
            typeArray[i] = (int) (Math.random() * length) % 2;
        }
    }

    public void display() {

        for (int i = 0; i < length; i++) {
            mPlantFactory.getPlant(typeArray[i]).display(xArray[i], yArray[i], ageArray[i]);
        }

    }
}
