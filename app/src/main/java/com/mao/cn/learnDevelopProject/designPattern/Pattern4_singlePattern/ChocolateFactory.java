package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 18:37
 */
public class ChocolateFactory {

    // 饱汉模式(懒汉模式)--双重加锁检查DCL（Double Check Lock）
    //  优点：懒加载，线程安全
    // 注：实例必须有 volatile 关键字修饰，其保证初始化完全。



    private boolean empty;
    private boolean boiled;

    /**
     * 对保存实例的变量添加volatile的修饰
     */
    public volatile static ChocolateFactory instance = null;

    private ChocolateFactory() {
        empty = true;
        boiled = false;
    }

    // 多线程
    public static ChocolateFactory getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (instance == null) {
            //同步块，线程安全的创建实例
            synchronized (ChocolateFactory.class) {
                //再次检查实例是否存在，如果不存在才真的创建实例
                if (instance == null) {
                    instance = new ChocolateFactory();
                }
            }
        }
        return instance;
    }

    public void fill() {
        if (empty) {
            // 添加原料巧克力动作
            empty = false;
            boiled = false;
        }
    }


    public void drain() {
        if ((!empty) && boiled) {
            // 排出巧克力动作
            empty = true;
        }
    }

    public void boil() {
        if ((!empty) && (!boiled)) {
            // 煮沸
            boiled = true;
        }
    }

}
