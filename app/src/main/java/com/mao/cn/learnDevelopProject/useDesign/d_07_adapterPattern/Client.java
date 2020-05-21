package com.mao.cn.learnDevelopProject.useDesign.d_07_adapterPattern;

/**
 * @author zhangkun
 * @time 2020-05-21 10:45
 * @Description 对象适配
 */
public class Client {

    public static void main(String[] args) {

        RMBAdapter rmbAdapter = new RMBAdapter(650);
        Adapter adapter = new Adapter(rmbAdapter);
        float rmb = rmbAdapter.getRMB();
        System.out.println("人民币  " + rmb);
        float usd = adapter.getUSD();
        System.out.println("美元 " + usd);
    }
}
