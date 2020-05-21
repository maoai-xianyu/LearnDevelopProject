package com.mao.cn.learnDevelopProject.useDesign.d_07_adapterPattern.classAdapter;

/**
 * @author zhangkun
 * @time 2020-05-21 10:54
 * @Description 把人民币 转换成 美元
 */
public class Adapter extends RMBAdapter implements USDTarget {
    public Adapter(float RMB) {
        super(RMB);
    }

    @Override
    public float getUSD() {
        return getRMB() / 5.6f;
    }

}
