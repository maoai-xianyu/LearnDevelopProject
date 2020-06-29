package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryAbs;

/**
 * @author zhangkun
 * @time 2020-05-18 17:22
 * @Description
 */
public interface IOFactory {

    public IOHandlerAbs createIOHandler(Class<? extends IOHandlerAbs> ioHandlerClass);
}
