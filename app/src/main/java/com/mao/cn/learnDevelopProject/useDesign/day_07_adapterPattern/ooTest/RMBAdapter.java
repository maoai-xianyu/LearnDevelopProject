package com.mao.cn.learnDevelopProject.useDesign.day_07_adapterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-21 10:42
 * @Description  代表第一个版本的人民
 */
public class RMBAdapter {

    private float mRMB;

    public RMBAdapter(float RMB) {
        mRMB = RMB;
    }

    public float getRMB() {
        return mRMB;
    }


    public float getUSD() {
        return mRMB / 5.6f;
    }
}

