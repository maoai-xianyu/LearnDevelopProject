package com.mao.cn.learnDevelopProject.component;

import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.modules.AppModule;
import com.mao.cn.learnDevelopProject.modules.DomainModule;
import com.mao.cn.learnDevelopProject.utils.tools.PreferenceU;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        DomainModule.class
})
public interface AppComponent {
    void inject(LearnDevelopApplication instance);

    PreferenceU getPreferenceU();
}