package com.mao.cn.learnDevelopProject.useDesign.d_07_adapterPattern.classAdapter;

/**
 * @author zhangkun
 * @time 2020-05-21 10:45
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 第二个版本兼容美元, 采用适配器模式
        // 角色：需要是适配的接口 Target 美元  适配器对象  adapter
        Adapter adapter = new Adapter(650);
        float rmb = adapter.getRMB();
        System.out.println("人民币  " + rmb);
        float usd = adapter.getUSD();
        System.out.println("美元 " + usd);
    }
}
