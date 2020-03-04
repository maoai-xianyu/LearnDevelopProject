package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 21:19
 */
public class SingletonNew {

    // 饱汉模式(懒汉模式)--线程安全
    // 优点：懒加载启动快，资源占用小，使用时才实例化，加锁
    //  缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。

    /**
     * 定义一个变量来存储创建好的类实例
     */

    private static SingletonNew singletonNew = null;

    /**
     * 私有化构造方法，好在内部控制创建实例的数目
     */
    private SingletonNew() {

    }

    /**
     * 定义一个方法来为客户端提供类实例
     *
     * @return 一个Singleton的实例
     */

    public static synchronized SingletonNew getInstance() {
        // /判断存储实例的变量是否有值
        if (singletonNew == null) {
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            singletonNew = new SingletonNew();
        }
        //如果有值，那就直接使用
        return singletonNew;
    }
}
