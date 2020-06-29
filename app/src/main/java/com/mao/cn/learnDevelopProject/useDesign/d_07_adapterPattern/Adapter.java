package com.mao.cn.learnDevelopProject.useDesign.d_07_adapterPattern;

/**
 * @author zhangkun
 * @time 2020-05-21 10:54
 * @Description 把人民币 转换成 美元
 */
public class Adapter implements USDTarget{


    private RMBAdapter mRMBAdapter;

    public Adapter(RMBAdapter RMBAdapter) {
        mRMBAdapter = RMBAdapter;
    }

    @Override
    public float getUSD() {
        return mRMBAdapter.getRMB() / 5.6f;
    }

}
