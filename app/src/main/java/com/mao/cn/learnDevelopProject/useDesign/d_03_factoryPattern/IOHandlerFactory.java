package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern;

/**
 * @author zhangkun
 * @time 2020-05-18 15:04
 * @Description 工厂设计模式 - 简单工厂设计模式
 */
public class IOHandlerFactory {

    public enum IOType {
        MEMORY,
        PREFERENCES
    }

    public static IOHandler createIOHandler(IOType ioType) {
        switch (ioType) {
            case MEMORY:
                // 直接返回一个对象，有的时候我们需要创建对象之后，需要进行一些了的初始化参数
                return new IOHandlerMemory();
            case PREFERENCES:
                return new IOHandlerPreferences();
            default:
                return null;
        }
    }

}
