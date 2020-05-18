package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod;

/**
 * @author zhangkun
 * @time 2020-05-18 15:46
 * @Description Preferences
 */
public class IOHandlerPreferencesFactory implements IOHandlerFactoryM {

    @Override
    public IOHandlerM createIOHandlerM() {
        return new IOHandlerPreferencesM();
    }
}
