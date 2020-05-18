package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryAbs;

/**
 * @author zhangkun
 * @time 2020-05-18 15:04
 * @Description 工厂设计模式 - 简单工厂设计模式
 * 1. 添加一个新的方式存储，需要改动的代码比较多
 */
public class IOHandlerFactoryAbsSingle implements IOFactory {


    private static volatile IOHandlerFactoryAbsSingle mInstance;
    private IOHandlerAbs isMemory, ioPreference;


    private IOHandlerFactoryAbsSingle() {

    }


    public static IOHandlerFactoryAbsSingle getInstance() {
        if (mInstance == null) {
            synchronized (IOHandlerFactoryAbsSingle.class) {
                if (mInstance == null) {
                    mInstance = new IOHandlerFactoryAbsSingle();
                }
            }
        }

        return mInstance;
    }

    @Override
    public IOHandlerAbs createIOHandler(Class<? extends IOHandlerAbs> ioHandlerClass) {

        try {
            return ioHandlerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new IOHandlerPreferencesAbs();
    }


    /**
     * 获取运行内存存储
     *
     * @return
     */
    public IOHandlerAbs getMemoryIOHandler() {
        if (isMemory == null) {
            isMemory = createIOHandler(IOHandlerMemoryAbs.class);
        }
        return isMemory;
    }


    /**
     * 获取 sp 存储
     *
     * @return
     */
    public IOHandlerAbs getPreferencesIOHandler() {
        if (ioPreference == null) {
            ioPreference = createIOHandler(IOHandlerPreferencesAbs.class);
        }
        return ioPreference;
    }


    /**
     * 获取默认的
     * why 搞个默认的？
     * 有时候写完了，但是网上有更好的更高效的
     * 为了方便切换
     *
     * @return
     */
    public IOHandlerAbs getDefaultIOHandler() {
        if (ioPreference == null) {
            ioPreference = createIOHandler(IOHandlerPreferencesAbs.class);
        }
        return ioPreference;
    }
}
