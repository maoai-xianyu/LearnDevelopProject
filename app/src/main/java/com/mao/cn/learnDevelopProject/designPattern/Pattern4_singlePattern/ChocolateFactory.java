package com.mao.cn.learnDevelopProject.designPattern.Pattern4_singlePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 18:37
 */
public class ChocolateFactory {
    private boolean empty;
    private boolean boiled;
    public volatile static ChocolateFactory instance;

    private ChocolateFactory() {
        empty = true;
        boiled = false;
    }

    // 多线程
    public static ChocolateFactory getInstance() {
        if (instance == null) {
            synchronized (ChocolateFactory.class) {
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
