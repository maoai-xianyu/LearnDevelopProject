package com.mao.cn.learnDevelopProject.useDesign.d_05_templatePattern;

/**
 * @author zhangkun
 * @time 2020-05-19 18:25
 * @Description
 */
public class Request implements Runnable,Comparable {

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Request 下载图片成功" + Thread.currentThread().getName());

    }
}
