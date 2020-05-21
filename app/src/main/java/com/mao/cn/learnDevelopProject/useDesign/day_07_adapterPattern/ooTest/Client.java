package com.mao.cn.learnDevelopProject.useDesign.day_07_adapterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-21 10:45
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 第一个版本人民币
        RMBAdapter rmbAdapter = new RMBAdapter(650);

        float rmb = rmbAdapter.getRMB();

        System.out.println("人民币  " + rmb);

        // 第二个版本兼容美元

        float sud = rmbAdapter.getUSD();

        System.out.println("美元 " + sud);
    }
}
