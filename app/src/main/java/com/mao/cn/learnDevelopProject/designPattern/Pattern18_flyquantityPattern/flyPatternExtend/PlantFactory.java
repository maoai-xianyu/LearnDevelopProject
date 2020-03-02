package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.flyPatternExtend;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-02 15:22
 */
public class PlantFactory {

    private HashMap<Integer, Plant> mIntegerPlantHashMap = new HashMap<>();

    public PlantFactory() {
    }

    public Plant getPlant(int type) {
        if (!mIntegerPlantHashMap.containsKey(type)) {
            switch (type) {
                case 0:
                    mIntegerPlantHashMap.put(0, new TreeNew());
                    break;
                case 1:
                    mIntegerPlantHashMap.put(1, new Grass());
                    break;
            }
        }
        return mIntegerPlantHashMap.get(type);
    }
}
