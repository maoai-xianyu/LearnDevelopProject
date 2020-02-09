package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.proxystatic;

/**
 * @author zhangkun
 * @time 2020-02-08 18:10
 */
public class ProxyObject extends AbstractObject {
    // 对目标类的引用
    private RealObject realObject;

    public ProxyObject(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    protected void operation() {
        System.out.println("do something before real operation ..");
        if (realObject == null) {
            realObject = new RealObject();
        }
        realObject.operation();
        System.out.println("do something after real operation ..");

    }
}
