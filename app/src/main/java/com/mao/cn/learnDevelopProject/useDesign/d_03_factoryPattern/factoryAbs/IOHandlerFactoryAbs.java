package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryAbs;

/**
 * @author zhangkun
 * @time 2020-05-18 15:04
 * @Description 工厂设计模式 - 简单工厂设计模式
 * 1. 添加一个新的方式存储，需要改动的代码比较多
 */
public class IOHandlerFactoryAbs {


    private static IOHandlerAbs createIOHandler(Class<? extends IOHandlerAbs> ioHandlerClass) {

        try {
            return ioHandlerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new IOHandlerPreferencesAbs();
    }


    /**
     * 获取运行内存存储
     * @return
     */
    public static IOHandlerAbs getMemoryIOHandler() {
        return createIOHandler(IOHandlerMemoryAbs.class);
    }


    /**
     * 获取 sp 存储
     * @return
     */
    public static IOHandlerAbs getPreferencesIOHandler() {
        return createIOHandler(IOHandlerPreferencesAbs.class);
    }


    /**
     * 获取默认的
     * why 搞个默认的？
     * 有时候写完了，但是网上有更好的更高效的
     * 为了方便切换
     * @return
     */
    public static IOHandlerAbs getDefaultIOHandler() {
        return createIOHandler(IOHandlerPreferencesAbs.class);
    }
}
